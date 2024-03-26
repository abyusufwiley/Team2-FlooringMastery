package com.sg.FlooringMastery.DAO;

import com.sg.FlooringMastery.DTO.OrderDTO;
import org.junit.jupiter.api.*;
import java.io.FileWriter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDAOImplTest {

    OrderDAO testDAO;

    public OrderDAOImplTest(){
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
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDAO = new OrderDAOImpl(testFile);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    @DisplayName("Test addOrder")
    public void testAddOrder() throws Exception {
        // ARRANGE
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

        // ACT
        testDAO.addOrder(order);

        OrderDTO retrievedOrder = testDAO.getOrder(orderId, LocalDate.parse("2024-03-26"));

        // ASSERT
        assertEquals(order, retrievedOrder, "The order should be added.");
        assertEquals(testDAO.getOrder(orderId, LocalDate.parse("2024-03-26")), order, "The retrieved order should be equal to the added order.");

        // Additional assertions to verify the properties of the retrieved order
        assertEquals(orderId, retrievedOrder.getOrderNumber());
        assertEquals("Ada Lovelace", retrievedOrder.getCustomerName());
        assertEquals("CA", retrievedOrder.getState());
        assertEquals(new BigDecimal("25.00"), retrievedOrder.getTaxRate());
        assertEquals("Tile", retrievedOrder.getProductType());
        assertEquals(new BigDecimal("249.00"), retrievedOrder.getArea());
        assertEquals(new BigDecimal("3.50"), retrievedOrder.getCostPerSquareFoot());
        assertEquals(new BigDecimal("4.15"), retrievedOrder.getLaborCostPerSquareFoot());
        assertEquals(new BigDecimal("871.50"), retrievedOrder.getMaterialCost());
        assertEquals(new BigDecimal("1033.35"), retrievedOrder.getLaborCost());
        assertEquals(new BigDecimal("476.21"), retrievedOrder.getTax());
        assertEquals(new BigDecimal("2381.06"), retrievedOrder.getTotal());
    }

    @Test
    @DisplayName("Test editOrder")
    public void testEditOrder() throws Exception {
        // ARRANGE
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

        // ACT
        OrderDTO addedOrder = testDAO.addOrder(order);
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

        testDAO.editOrder(editedOrder,editedOrder.getDate());
        OrderDTO updatedOrder = testDAO.getOrder(orderId,editedOrder.getDate());

        // ASSERT
        assertEquals(editedOrder, updatedOrder, "The order should be updated.");
        assertEquals(testDAO.getOrder(orderId, LocalDate.parse("2024-03-26")), editedOrder, "The retrieved order should be equal to the updated order.");

        // Additional assertions to verify the properties of the updated order
        assertEquals(orderId, updatedOrder.getOrderNumber());
        assertEquals("Grace Hopper", updatedOrder.getCustomerName());
        assertEquals("WA", updatedOrder.getState());
        assertEquals(new BigDecimal("9.25"), updatedOrder.getTaxRate());
        assertEquals("Carpet", updatedOrder.getProductType());
        assertEquals(new BigDecimal("243.00"), updatedOrder.getArea());
        assertEquals(new BigDecimal("2.25"), updatedOrder.getCostPerSquareFoot());
        assertEquals(new BigDecimal("2.10"), updatedOrder.getLaborCostPerSquareFoot());
        assertEquals(new BigDecimal("546.75"), updatedOrder.getMaterialCost());
        assertEquals(new BigDecimal("510.30"), updatedOrder.getLaborCost());
        assertEquals(new BigDecimal("50.54"), updatedOrder.getTax());
        assertEquals(new BigDecimal("1107.59"), updatedOrder.getTotal());
    }
//    @Test
//    @DisplayName("Test removeOrder")
//    public void testRemoveOrder() throws Exception {
//        // ARRANGE
//        int orderId = 1;
//        OrderDTO order = new OrderDTO(orderId);
//        order.setDate(LocalDate.parse("2024-03-26"));
//        order.setCustomerName("Ada Lovelace");
//        order.setState("CA");
//        order.setTaxRate(new BigDecimal("25.00"));
//        order.setProductType("Tile");
//        order.setArea(new BigDecimal("249.00"));
//        order.setCostPerSquareFoot(new BigDecimal("3.50"));
//        order.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
//        order.setMaterialCost(new BigDecimal("871.50"));
//        order.setLaborCost(new BigDecimal("1033.35"));
//        order.setTax(new BigDecimal("476.21"));
//        order.setTotal(new BigDecimal("2381.06"));
//
//        // ACT
//        OrderDTO addedOrder = testDAO.addOrder(order);
//        testDAO.removeOrder(orderId, order.getDate());
//
//        // ASSERT
//        OrderDTO removedOrder = testDAO.getOrder(orderId, order.getDate());
//        assertNull(removedOrder, "The order should be removed.");
//    }

    @Test
    public void testRemoveOrder() throws Exception {
        // Create two new orders
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

        // Add both to the DAO
        testDAO.addOrder(firstOrder);
        testDAO.addOrder(secondOrder);

        // remove the first order - Ada's
        OrderDTO removedOrder = testDAO.removeOrder(firstOrder.getOrderNumber(), firstOrder.getDate());

        // Check that the correct object was removed.
        assertEquals(removedOrder, firstOrder, "The removed order should be Ada's.");

        // Get all the orders
        List<OrderDTO> allOrders = testDAO.getAllOrders();

        // First check the general contents of the list
        assertNotNull(allOrders, "All orders list should be not null.");
        assertEquals(1, allOrders.size(), "All orders should only have 1 order.");

        // Then the specifics
        assertFalse(allOrders.contains(firstOrder), "All orders should NOT include Ada's.");
        assertTrue(allOrders.contains(secondOrder), "All orders should include Charles's.");

        // Remove the second order
        removedOrder = testDAO.removeOrder(secondOrder.getOrderNumber(), secondOrder.getDate());
        // Check that the correct object was removed.
        assertEquals(removedOrder, secondOrder, "The removed order should be Charles's.");

        // retrieve all the orders again, and check the list.
        allOrders = testDAO.getAllOrders();

        // Check the contents of the list - it should be empty
        assertTrue(allOrders.isEmpty(), "The retrieved list of orders should be empty.");

        // Try to 'get' both orders by their old id - they should be null!
        OrderDTO retrievedOrder = testDAO.getOrder(firstOrder.getOrderNumber(), firstOrder.getDate());
        assertNull(retrievedOrder, "Ada's order was removed, should be null.");

        retrievedOrder = testDAO.getOrder(secondOrder.getOrderNumber(), secondOrder.getDate());
        assertNull(retrievedOrder, "Charles's order was removed, should be null.");
    }
}