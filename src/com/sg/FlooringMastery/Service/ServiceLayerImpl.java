package com.sg.FlooringMastery.Service;

import com.sg.FlooringMastery.DAO.OrderDAO;
import com.sg.FlooringMastery.DAO.OrderDAOException;
import com.sg.FlooringMastery.DAO.ProductDAO;
import com.sg.FlooringMastery.DAO.ProductDAOException;
import com.sg.FlooringMastery.DAO.TaxDAO;
import com.sg.FlooringMastery.DTO.OrderDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ServiceLayerImpl implements ServiceLayer{
    private OrderDAO orderDAO;
    private TaxDAO taxDAO;
    private ProductDAO productDAO;


    public ServiceLayerImpl(OrderDAO orderDAO, ProductDAO productDAO, TaxDAO taxDAO){
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
        this.taxDAO = taxDAO;
    }

    @Override
    public OrderDTO getOrder(int orderNumber, LocalDate date) throws OrderDAOException{
        //get order by order id and name
            return orderDAO.getOrder(orderNumber, date);
      
    }

    @Override
    public List<OrderDTO> getAllOrders() throws OrderDAOException{
        return orderDAO.getAllOrders();
    }

    @Override
    public OrderDTO addOrder(OrderDTO order) throws OrderDAOException{
        validateOrderData(order);
        order.setMaterialCost(calculateMaterialCost(order));
        order.setLaborCost(calculateLaborCost(order));
        order.setTax(calculateTax(order));
        order.setTaxRate(taxDAO.getTax(order.getState()).getTaxRate());
        order.setCostPerSquareFoot(productDAO.getProduct(order.getProductType()).getCostPerSquareFoot());
        order.setLaborCostPerSquareFoot(productDAO.getProduct(order.getProductType()).getLaborCostPerSquareFoot());
        order.setTotal(calculateTotal(order));
        
        
        return orderDAO.addOrder(order);
    }

    private BigDecimal calculateTotal(OrderDTO order) {
        return order.getMaterialCost().add(order.getLaborCost()).add(order.getTax());
    }

    private BigDecimal calculateTax(OrderDTO order) {
        return order.getMaterialCost().add(order.getLaborCost()).multiply(taxDAO.getTax(order.getState()).getTaxRate().divide(BigDecimal.valueOf(100)));
    }

    private BigDecimal calculateLaborCost(OrderDTO order) throws ProductDAOException {
        return order.getArea().multiply(productDAO.getProduct(order.getProductType()).getLaborCostPerSquareFoot());
    }

    private BigDecimal calculateMaterialCost(OrderDTO order) throws ProductDAOException {
        return order.getArea().multiply(productDAO.getProduct(order.getProductType()).getCostPerSquareFoot());
    }

    private void validateOrderData(OrderDTO order) throws OrderDAOException{
        if(order.getCustomerName() == null
                || order.getCustomerName().trim().length() == 0
                || order.getState() == null
                || order.getState().trim().length() == 0
                || !validateState(order.getState())
                || order.getProductType() == null
                || order.getProductType().trim().length() == 0
                || order.getArea() == null
                //|| order.getArea().compareTo(BigDecimal.valueOf(0)) <= 0 
                || order.getArea().compareTo(BigDecimal.valueOf(0)) <= 0 ){ //|| order.getArea().compareTo(BigDecimal.valueOf(100)) < 0{
                //|| order.getDate().isBefore(LocalDate.now())){
            //throw new OrderDAOException("ERROR: All fields [Customer Name, State, Product Type, Area] are required. Area must be greater than 100. Date must be in the future.");
        }
    }

    //method to validate date input with the file name
    

    private boolean validateState(String state){
        return taxDAO.getTax(state) != null;
    }

    @Override
    public OrderDTO editOrder(OrderDTO order, LocalDate date) throws OrderDAOException{ 
        validateOrderData(order);
        return orderDAO.editOrder(order, date) ;
    }

    @Override
    public OrderDTO removeOrder(int orderNumber, LocalDate date) throws OrderDAOException{
        return orderDAO.removeOrder(orderNumber, date);
    }

    @Override
    public void exportAllData() throws OrderDAOException{
        orderDAO.exportAllData();
    }

}

