package com.sg.FlooringMastery.Test.ServiceTest;


import com.sg.FlooringMastery.DAO.OrderDAO;
import com.sg.FlooringMastery.DAO.OrderDAOException;
import com.sg.FlooringMastery.DTO.OrderDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderDAOStubImpl implements OrderDAO {
    public OrderDTO onlyOrder;

    public OrderDAOStubImpl() {
        this.onlyOrder = new OrderDTO(1);
        this.onlyOrder.setDate(LocalDate.parse("2024-03-26"));
        this.onlyOrder.setCustomerName("Ada Lovelace");
        this.onlyOrder.setState("CA");
        this.onlyOrder.setTaxRate(new BigDecimal("25.00"));
        this.onlyOrder.setProductType("Tile");
        this.onlyOrder.setArea(new BigDecimal("249.00"));
        this.onlyOrder.setCostPerSquareFoot(new BigDecimal("3.50"));
        this.onlyOrder.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
        this.onlyOrder.setMaterialCost(new BigDecimal("871.50"));
        this.onlyOrder.setLaborCost(new BigDecimal("1033.35"));
        this.onlyOrder.setTax(new BigDecimal("476.21"));
        this.onlyOrder.setTotal(new BigDecimal("2381.06"));
    }

    public OrderDAOStubImpl(OrderDTO testOrder) {
        this.onlyOrder = testOrder;
    }

    public OrderDTO getOrder(int orderId, LocalDate date) throws OrderDAOException {
        return orderId == this.onlyOrder.getOrderNumber() ? this.onlyOrder : null;
    }

    public List<OrderDTO> getAllOrders() throws OrderDAOException {
        List<OrderDTO> orderList = List.of(this.onlyOrder);
        return orderList;
    }

    public OrderDTO addOrder(OrderDTO order) throws OrderDAOException {
        return order.getOrderNumber() == this.onlyOrder.getOrderNumber() ? this.onlyOrder : null;
    }

    public OrderDTO editOrder(OrderDTO order, LocalDate date) throws OrderDAOException {
        return order.getOrderNumber() == this.onlyOrder.getOrderNumber() ? this.onlyOrder : null;
    }

    public OrderDTO removeOrder(int orderId, LocalDate date) throws OrderDAOException {
        return orderId == this.onlyOrder.getOrderNumber() ? this.onlyOrder : null;
    }

    public List<OrderDTO> getOrdersByDate(LocalDate date) throws OrderDAOException {
        List<OrderDTO> orderList = List.of(this.onlyOrder);
        return orderList;
    }

    public void exportAllData() throws OrderDAOException {
    }
}
