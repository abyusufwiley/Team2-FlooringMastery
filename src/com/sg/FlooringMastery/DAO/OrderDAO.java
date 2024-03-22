package com.sg.FlooringMastery.DAO;

import java.time.LocalDate;
import java.util.List;

import com.sg.FlooringMastery.DTO.OrderDTO;

public interface OrderDAO {
    OrderDTO getOrder(int orderId, String name) throws OrderDAOException;

    List<OrderDTO> getAllOrders() throws OrderDAOException;


    OrderDTO addOrder(OrderDTO order) throws OrderDAOException;

    OrderDTO editOrder(int orderId, String name) throws OrderDAOException;

    OrderDTO removeOrder(int orderId, LocalDate date) throws OrderDAOException;

}
