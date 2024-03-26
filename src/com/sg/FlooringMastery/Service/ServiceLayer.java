package com.sg.FlooringMastery.Service;

import com.sg.FlooringMastery.DAO.OrderDAOException;
import com.sg.FlooringMastery.DAO.ProductDAOException;
import com.sg.FlooringMastery.DTO.OrderDTO;

import java.time.LocalDate;
import java.util.List;


public interface ServiceLayer {
    OrderDTO getOrder(int orderNumber, LocalDate date) throws OrderDAOException;
    List<OrderDTO> getAllOrders() throws OrderDAOException;
    OrderDTO addOrder(OrderDTO order) throws OrderDAOException;
    OrderDTO editOrder(OrderDTO order, LocalDate date) throws OrderDAOException;
    OrderDTO removeOrder(int orderNumber, LocalDate date) throws OrderDAOException;
    List<OrderDTO> getOrdersByDate(LocalDate date) throws OrderDAOException;
    List<String> getValidStates();
    List<String> getValidProducts() throws ProductDAOException;
    void exportAllData() throws OrderDAOException;

}
