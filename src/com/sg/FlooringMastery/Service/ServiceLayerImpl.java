package com.sg.FlooringMastery.Service;

import com.sg.FlooringMastery.DAO.OrderDAO;
import com.sg.FlooringMastery.DAO.ProductDAO;
import com.sg.FlooringMastery.DAO.TaxDAO;
import com.sg.FlooringMastery.DTO.OrderDTO;

import java.time.LocalDate;
import java.util.List;

public class ServiceLayerImpl implements ServiceLayer{
    private OrderDAO orderDAO;
    private TaxDAO taxDAO;
    private ProductDAO productDAO;


    @Override
    public OrderDTO getOrder(int orderId, String name) {
        return null;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return null;
    }

    @Override
    public OrderDTO addOrder(OrderDTO order) {
        return null;
    }

    @Override
    public OrderDTO editOrder(OrderDTO order) {
        return null;
    }

    @Override
    public OrderDTO removeOrder(int orderId, LocalDate date) {
        return null;
    }
}
