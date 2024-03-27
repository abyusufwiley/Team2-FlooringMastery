package com.sg.FlooringMastery.Test.ServiceTest;

import com.sg.FlooringMastery.DAO.OrderDAO;
import com.sg.FlooringMastery.DAO.OrderDAOException;
import com.sg.FlooringMastery.DAO.ProductDAO;
import com.sg.FlooringMastery.DAO.TaxDAO;
import com.sg.FlooringMastery.DTO.OrderDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.sg.FlooringMastery.Service.ServiceLayerImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ServiceLayerImplTest {
    private ServiceLayerImpl service;

    public ServiceLayerImplTest() {
        OrderDAO dao = new OrderDAOStubImpl();
        ProductDAO productDAO = new ProductDAOStubImpl();
        TaxDAO taxDAO = new TaxDAOStubImpl();
        this.service = new ServiceLayerImpl(dao, productDAO, taxDAO);
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddOrder() {
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

        try {
            this.service.addOrder(order);
        } catch (OrderDAOException var4) {
            Assertions.fail("Order was valid. No exception should have been thrown.");
        }

    }

    @Test
    public void testGetOrder() {
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

        try {
            OrderDTO retrievedOrder = this.service.getOrder(orderId, order.getDate());
            Assertions.assertEquals(order, retrievedOrder, "Checking retrieved order.");
        } catch (OrderDAOException var4) {
            Assertions.fail("Order was valid. No exception should have been thrown.");
        }

    }

    @Test
    public void testGetAllOrders() {
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

        try {
            List<OrderDTO> orderList = this.service.getAllOrders();
            Assertions.assertEquals(1, orderList.size(), "Checking size of order list.");
            Assertions.assertTrue(orderList.contains(order), "Checking order in list.");
        } catch (OrderDAOException var4) {
            Assertions.fail("Order was valid. No exception should have been thrown.");
        }

    }

    @Test
    public void testEditOrder() {
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

        try {
            OrderDTO editedOrder = this.service.editOrder(order, order.getDate());
            Assertions.assertEquals(order, editedOrder, "Checking edited order.");
        } catch (OrderDAOException var4) {
            Assertions.fail("Order was valid. No exception should have been thrown.");
        }

    }

    @Test
    public void testRemoveOrder() {
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

        try {
            OrderDTO removedOrder = this.service.removeOrder(orderId, order.getDate());
            Assertions.assertEquals(order, removedOrder, "Checking removed order.");
        } catch (OrderDAOException var4) {
            Assertions.fail("Order was valid. No exception should have been thrown.");
        }

    }

    @Test
    public void testGetOrdersByDate() {
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

        try {
            List<OrderDTO> orderList = this.service.getOrdersByDate(order.getDate());
            Assertions.assertEquals(1, orderList.size(), "Checking size of order list.");
            Assertions.assertTrue(orderList.contains(order), "Checking order in list.");
        } catch (OrderDAOException var4) {
            Assertions.fail("Order was valid. No exception should have been thrown.");
        }

    }

    @Test
    public void testExportAllData() {
        try {
            this.service.exportAllData();
        } catch (OrderDAOException var2) {
            Assertions.fail("Data was valid. No exception should have been thrown.");
        }

    }
}
