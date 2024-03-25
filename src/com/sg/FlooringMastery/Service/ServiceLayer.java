package com.sg.FlooringMastery.Service;

import com.sg.FlooringMastery.DTO.OrderDTO;

import java.time.LocalDate;
import java.util.List;


public interface ServiceLayer {
    OrderDTO getOrder(int orderId, String name);
    List<OrderDTO> getAllOrders();
    OrderDTO addOrder(OrderDTO order);
    OrderDTO editOrder(OrderDTO order);
    OrderDTO removeOrder(int orderId, LocalDate date);



}
