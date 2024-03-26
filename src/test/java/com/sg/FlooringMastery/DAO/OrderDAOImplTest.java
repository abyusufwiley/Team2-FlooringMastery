package com.sg.FlooringMastery.DAO;

import com.sg.FlooringMastery.DTO.OrderDTO;
import org.junit.jupiter.api.*;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("OrderDAOImplTest tests")
class OrderDAOImplTest {

    OrderDAO orderDAO;

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
        orderDAO = new OrderDAOImpl(testFile);
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
        OrderDTO addedOrder = orderDAO.addOrder(order);

        // ASSERT
        assertEquals(order, addedOrder, "The order should be added.");
        assertEquals(orderDAO.getOrder(orderId), order, "The retrieved order should be equal to the added order.");
    }




}