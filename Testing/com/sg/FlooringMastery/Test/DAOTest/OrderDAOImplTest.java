package com.sg.FlooringMastery.Test.DAOTest;

import com.sg.FlooringMastery.DAO.OrderDAO;
import com.sg.FlooringMastery.DAO.OrderDAOImpl;
import com.sg.FlooringMastery.DTO.OrderDTO;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderDAOImplTest {
    OrderDAO testDAO;

    public OrderDAOImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testroster.txt";
        new FileWriter(testFile);
        this.testDAO = new OrderDAOImpl(testFile);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    @DisplayName("Test addOrder")
    public void testAddOrder() throws Exception {
        int orderId = 1;
        OrderDTO order = new OrderDTO(orderId);
        order.setDate(LocalDate.parse("2024-03-26"));
        order.setCustomerName("Ada Lovelace");
        order.setState("CA");
        order.setTaxRate(new BigDecimal("25.00"));
        order.setProductType("Tile");
        order.setArea(new BigDecimal("249.00"));
        order.setCostPerSquareFoot(new BigDecimal("3.50"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
        order.setMaterialCost(new BigDecimal("871.50"));
        order.setLaborCost(new BigDecimal("1033.35"));
        order.setTax(new BigDecimal("476.21"));
        order.setTotal(new BigDecimal("2381.06"));
        this.testDAO.addOrder(order);
        OrderDTO retrievedOrder = this.testDAO.getOrder(orderId, LocalDate.parse("2024-03-26"));
        Assertions.assertEquals(order, retrievedOrder, "The order should be added.");
        Assertions.assertEquals(this.testDAO.getOrder(orderId, LocalDate.parse("2024-03-26")), order, "The retrieved order should be equal to the added order.");
        Assertions.assertEquals(orderId, retrievedOrder.getOrderNumber());
        Assertions.assertEquals("Ada Lovelace", retrievedOrder.getCustomerName());
        Assertions.assertEquals("CA", retrievedOrder.getState());
        Assertions.assertEquals(new BigDecimal("25.00"), retrievedOrder.getTaxRate());
        Assertions.assertEquals("Tile", retrievedOrder.getProductType());
        Assertions.assertEquals(new BigDecimal("249.00"), retrievedOrder.getArea());
        Assertions.assertEquals(new BigDecimal("3.50"), retrievedOrder.getCostPerSquareFoot());
        Assertions.assertEquals(new BigDecimal("4.15"), retrievedOrder.getLaborCostPerSquareFoot());
        Assertions.assertEquals(new BigDecimal("871.50"), retrievedOrder.getMaterialCost());
        Assertions.assertEquals(new BigDecimal("1033.35"), retrievedOrder.getLaborCost());
        Assertions.assertEquals(new BigDecimal("476.21"), retrievedOrder.getTax());
        Assertions.assertEquals(new BigDecimal("2381.06"), retrievedOrder.getTotal());
    }

    @Test
    @DisplayName("Test editOrder")
    public void testEditOrder() throws Exception {
        int orderId = 1;
        OrderDTO order = new OrderDTO(orderId);
        order.setDate(LocalDate.parse("2024-03-26"));
        order.setCustomerName("Ada Lovelace");
        order.setState("CA");
        order.setTaxRate(new BigDecimal("25.00"));
        order.setProductType("Tile");
        order.setArea(new BigDecimal("249.00"));
        order.setCostPerSquareFoot(new BigDecimal("3.50"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
        order.setMaterialCost(new BigDecimal("871.50"));
        order.setLaborCost(new BigDecimal("1033.35"));
        order.setTax(new BigDecimal("476.21"));
        order.setTotal(new BigDecimal("2381.06"));
        this.testDAO.addOrder(order);
        OrderDTO editedOrder = new OrderDTO(orderId);
        editedOrder.setCustomerName("Grace Hopper");
        editedOrder.setDate(LocalDate.parse("2024-03-26"));
        editedOrder.setState("WA");
        editedOrder.setTaxRate(new BigDecimal("9.25"));
        editedOrder.setProductType("Carpet");
        editedOrder.setArea(new BigDecimal("243.00"));
        editedOrder.setCostPerSquareFoot(new BigDecimal("2.25"));
        editedOrder.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        editedOrder.setMaterialCost(new BigDecimal("546.75"));
        editedOrder.setLaborCost(new BigDecimal("510.30"));
        editedOrder.setTax(new BigDecimal("50.54"));
        editedOrder.setTotal(new BigDecimal("1107.59"));
        this.testDAO.editOrder(editedOrder, editedOrder.getDate());
        OrderDTO updatedOrder = this.testDAO.getOrder(orderId, editedOrder.getDate());
        Assertions.assertEquals(editedOrder, updatedOrder, "The order should be updated.");
        Assertions.assertEquals(this.testDAO.getOrder(orderId, LocalDate.parse("2024-03-26")), editedOrder, "The retrieved order should be equal to the updated order.");
        Assertions.assertEquals(orderId, updatedOrder.getOrderNumber());
        Assertions.assertEquals("Grace Hopper", updatedOrder.getCustomerName());
        Assertions.assertEquals("WA", updatedOrder.getState());
        Assertions.assertEquals(new BigDecimal("9.25"), updatedOrder.getTaxRate());
        Assertions.assertEquals("Carpet", updatedOrder.getProductType());
        Assertions.assertEquals(new BigDecimal("243.00"), updatedOrder.getArea());
        Assertions.assertEquals(new BigDecimal("2.25"), updatedOrder.getCostPerSquareFoot());
        Assertions.assertEquals(new BigDecimal("2.10"), updatedOrder.getLaborCostPerSquareFoot());
        Assertions.assertEquals(new BigDecimal("546.75"), updatedOrder.getMaterialCost());
        Assertions.assertEquals(new BigDecimal("510.30"), updatedOrder.getLaborCost());
        Assertions.assertEquals(new BigDecimal("50.54"), updatedOrder.getTax());
        Assertions.assertEquals(new BigDecimal("1107.59"), updatedOrder.getTotal());
    }

    @Test
    public void testRemoveOrder() throws Exception {
        OrderDTO firstOrder = new OrderDTO(1);
        firstOrder.setDate(LocalDate.parse("2024-03-26"));
        firstOrder.setCustomerName("Ada Lovelace");
        firstOrder.setState("CA");
        firstOrder.setTaxRate(new BigDecimal("25.00"));
        firstOrder.setProductType("Tile");
        firstOrder.setArea(new BigDecimal("249.00"));
        firstOrder.setCostPerSquareFoot(new BigDecimal("3.50"));
        firstOrder.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
        firstOrder.setMaterialCost(new BigDecimal("871.50"));
        firstOrder.setLaborCost(new BigDecimal("1033.35"));
        firstOrder.setTax(new BigDecimal("476.21"));
        firstOrder.setTotal(new BigDecimal("2381.06"));
        OrderDTO secondOrder = new OrderDTO(2);
        secondOrder.setCustomerName("Grace Hopper");
        secondOrder.setDate(LocalDate.parse("2024-03-26"));
        secondOrder.setState("WA");
        secondOrder.setTaxRate(new BigDecimal("9.25"));
        secondOrder.setProductType("Carpet");
        secondOrder.setArea(new BigDecimal("243.00"));
        secondOrder.setCostPerSquareFoot(new BigDecimal("2.25"));
        secondOrder.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        secondOrder.setMaterialCost(new BigDecimal("546.75"));
        secondOrder.setLaborCost(new BigDecimal("510.30"));
        secondOrder.setTax(new BigDecimal("50.54"));
        secondOrder.setTotal(new BigDecimal("1107.59"));
        this.testDAO.addOrder(firstOrder);
        this.testDAO.addOrder(secondOrder);
        OrderDTO removedOrder = this.testDAO.removeOrder(firstOrder.getOrderNumber(), firstOrder.getDate());
        Assertions.assertEquals(removedOrder, firstOrder, "The removed order should be Ada's.");
        List<OrderDTO> allOrders = this.testDAO.getAllOrders(); // Make sure this method correctly retrieves all orders
        Assertions.assertEquals(1, allOrders.size(), "All orders should only have 1 order after removal.");
        Assertions.assertTrue(allOrders.stream().anyMatch(order -> order.getOrderNumber() == secondOrder.getOrderNumber()), "All orders should include Charles's.");
        removedOrder = this.testDAO.removeOrder(secondOrder.getOrderNumber(), secondOrder.getDate());
        Assertions.assertEquals(removedOrder, secondOrder, "The removed order should be Charles's.");
        allOrders = this.testDAO.getAllOrders(); // Refresh the list of all orders after removal
        Assertions.assertTrue(allOrders.isEmpty(), "The retrieved list of orders should be empty after both removals.");
    }

}
