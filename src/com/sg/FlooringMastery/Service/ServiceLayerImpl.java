package com.sg.FlooringMastery.Service;

import com.sg.FlooringMastery.DAO.OrderDAO;
import com.sg.FlooringMastery.DAO.OrderDAOException;
import com.sg.FlooringMastery.DAO.ProductDAO;
import com.sg.FlooringMastery.DAO.ProductDAOException;
import com.sg.FlooringMastery.DAO.TaxDAO;
import com.sg.FlooringMastery.DTO.OrderDTO;
import com.sg.FlooringMastery.DTO.ProductDTO;
import com.sg.FlooringMastery.DTO.TaxDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Component
public class ServiceLayerImpl implements ServiceLayer{
    private OrderDAO orderDAO; // The DAO for orders
    private TaxDAO taxDAO; // The DAO for taxes
    private ProductDAO productDAO; // The DAO for products

@Autowired
    public ServiceLayerImpl(OrderDAO orderDAO, ProductDAO productDAO, TaxDAO taxDAO){
    // Constructor that initializes the DAOs
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
        this.taxDAO = taxDAO;
    }

    @Override
    // getOrder() retrieves an order by order number and date
    public OrderDTO getOrder(int orderNumber, LocalDate date) throws OrderDAOException{
        //get order by order id and name
        return orderDAO.getOrder(orderNumber, date);

    }

    @Override
    // getAllOrders() retrieves all orders
    public List<OrderDTO> getAllOrders() throws OrderDAOException{
        return orderDAO.getAllOrders();
    }

    @Override
    // addOrder() adds a new order
    public OrderDTO addOrder(OrderDTO order) throws OrderDAOException{
        if (order.getDate() == null) {
            throw new OrderDAOException("Order date cannot be null.");
        }
        //validate order data
        validateOrderData(order);
        order.setMaterialCost(calculateMaterialCost(order));
        order.setLaborCost(calculateLaborCost(order));
        order.setTax(calculateTax(order));
        order.setTaxRate(taxDAO.getTax(order.getState()).getTaxRate());
        order.setCostPerSquareFoot(productDAO.getProduct(order.getProductType()).getCostPerSquareFoot());
        order.setLaborCostPerSquareFoot(productDAO.getProduct(order.getProductType()).getLaborCostPerSquareFoot());
        order.setTotal(calculateTotal(order));


        return orderDAO.addOrder(order); // Add the order to the storage
    }

    private BigDecimal calculateTotal(OrderDTO order) { // Calculate the total cost of an order
        return order.getMaterialCost().add(order.getLaborCost()).add(order.getTax());
    }

    private BigDecimal calculateTax(OrderDTO order) { //Calculate the tax for an order
        return order.getMaterialCost().add(order.getLaborCost()).multiply(taxDAO.getTax(order.getState()).getTaxRate().divide(BigDecimal.valueOf(100)));
    }

    private BigDecimal calculateLaborCost(OrderDTO order) throws ProductDAOException { // Calculate the labor cost for an order
        return order.getArea().multiply(productDAO.getProduct(order.getProductType()).getLaborCostPerSquareFoot());
    }

    private BigDecimal calculateMaterialCost(OrderDTO order) throws ProductDAOException { // Calculate the material cost for an order
        return order.getArea().multiply(productDAO.getProduct(order.getProductType()).getCostPerSquareFoot());
    }

    private void validateOrderData(OrderDTO order) throws OrderDAOException{
    // Validate the order data by checking if all fields are present and if the area is greater than 100
        if(order.getCustomerName() == null
                || order.getCustomerName().trim().length() == 0
                || order.getState() == null
                || order.getState().trim().length() == 0
                || !validateState(order.getState())
                || order.getProductType() == null
                || order.getProductType().trim().length() == 0
                || order.getArea() == null
                || order.getArea().compareTo(BigDecimal.valueOf(0)) <= 0 ){ //|| order.getArea().compareTo(BigDecimal.valueOf(100)) < 0{

        }
        if (order.getDate() == null || order.getDate().isBefore(LocalDate.now())) {
            throw new OrderDAOException("ERROR: Order date is not set or must be in the future.");
        }
        if(order.getProductType() == null || order.getState() == null){
            throw new OrderDAOException("ERROR: Please Enter a Valid State Code or Product");

        }

    }


    public List<String> getValidStates() {// Get a list of valid state codes
        // Invoke the DAO to get all taxes and then extract the state codes
        List<TaxDTO> taxList = taxDAO.getAllTaxes();
        List<String> validStates = new ArrayList<>();
        for (TaxDTO tax : taxList) {
            validStates.add(tax.getState());
        }
        return validStates;
    }

    public List<String> getValidProducts() throws ProductDAOException {
    // Get a list of valid products by invoking the DAO to get all products and then extracting the product types
        List<ProductDTO> productList = productDAO.getAllProducts();
        List<String> validProducts = new ArrayList<>();
        for (ProductDTO product : productList) {
            validProducts.add(product.getProductType());
        }
        return validProducts;
    }
    private boolean validateState(String state){
    // Validate a state by checking if it is present in the taxes map
        return taxDAO.getTax(state) != null;
    }
    private boolean validateProduct(String productType) throws ProductDAOException {
    // Validate a product by checking if it is present in the products map
        return productDAO.getProduct(productType) != null;
    }

    @Override
    public OrderDTO editOrder(OrderDTO order, LocalDate date) throws OrderDAOException{
    // Edit an order by validating the data and then invoking the DAO to edit the order
        if (order.getDate() == null) {
            order.setDate(date);
        }
        validateOrderData(order);
        return orderDAO.editOrder(order, date) ;
    }

    @Override
    public OrderDTO removeOrder(int orderNumber, LocalDate date) throws OrderDAOException{
    // Remove an order by invoking the DAO to remove the order
        return orderDAO.removeOrder(orderNumber, date);
    }

    @Override
    // exportAllData() exports all data to the backup file
    public void exportAllData() throws OrderDAOException{
        orderDAO.exportAllData();
    }
    public List<OrderDTO> getOrdersByDate(LocalDate date) throws OrderDAOException {
    // Get all orders for a specific date by invoking the DAO
        return orderDAO.getOrdersByDate(date);
    }

}


