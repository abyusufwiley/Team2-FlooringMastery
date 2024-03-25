package com.sg.FlooringMastery.Controller;

import java.time.LocalDate;
import java.util.List;

import com.sg.FlooringMastery.DAO.OrderDAOException;
import com.sg.FlooringMastery.DTO.OrderDTO;
import com.sg.FlooringMastery.Service.ServiceLayer;
import com.sg.FlooringMastery.UI.View;

//hello
public class Controller {
    private View view ;
    private ServiceLayer orderService;

    public Controller(View view, ServiceLayer serviceLayer) {
        this.view = view;
        this.orderService = serviceLayer;
    }

    public Controller() {
    }


    public void run() throws OrderDAOException {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    displayOrders();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    exportAllData();
                    break;
                case 6:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }

        }
        exitMessage();
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void displayOrders() throws OrderDAOException {
        view.displayOrdersBanner();
        List<OrderDTO> orders = orderService.getAllOrders();
        view.displayOrders(orders);
    }

    private void addOrder() throws OrderDAOException {
        view.addOrderBanner();
        OrderDTO newOrder = view.getNewOrderInfo();
        orderService.addOrder(newOrder);
        view.displayOrdersBanner();
    }

    private void editOrder() throws OrderDAOException {
        view.editOrderBanner();
        int orderId = view.getOrderNumber();
        LocalDate date = view.getDate();
        OrderDTO order = orderService.getOrder(orderId, date);
        OrderDTO editedOrder = view.editOrderInfo(order);
        orderService.editOrder(editedOrder, date);
        view.displayOrdersBanner();
    }


    private void removeOrder() throws OrderDAOException {
        view.removeOrderBanner();
        int orderId = view.getOrderNumber();
        LocalDate date = view.getDate();
        OrderDTO order = orderService.getOrder(orderId, date);
        orderService.removeOrder(orderId, order.getDate());
        view.displayOrdersBanner();
    }

    private void exportAllData() throws OrderDAOException {
        view.exportAllDataBanner();
        orderService.exportAllData();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void errorMessage(String errorMsg) {
        view.displayErrorMessage(errorMsg);
    }

}
