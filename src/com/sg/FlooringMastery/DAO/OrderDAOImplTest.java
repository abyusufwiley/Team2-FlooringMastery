package com.sg.FlooringMastery.DAO;

import com.sg.FlooringMastery.DTO.OrderDTO;
import org.junit.jupiter.api.*;
import java.io.FileWriter;

import java.math.BigDecimal;
import java.time.LocalDate;

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
//        testDAO.removeOrder(orderId, addedOrder.getDate());
//
//        // ASSERT
//        OrderDTO removedOrder = testDAO.getOrder(orderId, addedOrder.getDate());
//        System.out.println(removedOrder);
//        assertNull(removedOrder, "The order should be removed.");
//    }

    @Test
    @DisplayName("Test removeOrder")
    public void testRemoveOrder() throws Exception {
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
        OrderDTO removedOrder = testDAO.removeOrder(orderId, addedOrder.getDate());

        // ASSERT
        assertEquals(order, addedOrder, "The order should be added.");
        assertEquals(order, removedOrder, "The order should be removed.");
        assertNull(testDAO.getOrder(orderId,addedOrder.getDate()), "The order should be null.");
    }

}