package com.sg.FlooringMastery.DAO;

import java.time.LocalDate;
import java.util.List;
import com.sg.FlooringMastery.DTO.OrderDTO;

// Defines the interface for data access operations related to orders
public interface OrderDAO {
    // Retrieves a specific order by ID and date
    OrderDTO getOrder(int orderId, LocalDate date) throws OrderDAOException;

    // Retrieves all orders
    List<OrderDTO> getAllOrders() throws OrderDAOException;

    // Adds a new order to the storage
    OrderDTO addOrder(OrderDTO order) throws OrderDAOException;


    OrderDTO editOrder(OrderDTO order, LocalDate date) throws OrderDAOException;  // Updates an existing order.

    // Removes an order from the storage
    OrderDTO removeOrder(int orderId, LocalDate date) throws OrderDAOException;

    List<OrderDTO> getOrdersByDate(LocalDate date) throws OrderDAOException; // Retrieves all orders for a specific date

    void exportAllData() throws OrderDAOException; // Exports all order data
}
