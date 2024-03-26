package com.sg.FlooringMastery.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.sg.FlooringMastery.DAO.OrderDAOException;
import com.sg.FlooringMastery.DTO.OrderDTO;
import com.sg.FlooringMastery.Service.ServiceLayer;
import com.sg.FlooringMastery.UI.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Controller {
    private View view ;
    private ServiceLayer orderService;
@Autowired
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
        LocalDate date = view.getDate();
        List<OrderDTO> orders = orderService.getOrdersByDate(date);
        if (orders.isEmpty()) {
            view.displayErrorMessage("No orders found for the date: " + date.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
        } else {
            view.displayOrders(orders);
        }
    }

    private void addOrder() throws OrderDAOException {
        boolean isValid = false;
        while (!isValid) {
            try {
                view.addOrderBanner();
                List<String> validStates = orderService.getValidStates();
                List<String> validProducts = orderService.getValidProducts();
                OrderDTO newOrder = view.getNewOrderInfo(validStates, validProducts);
                orderService.addOrder(newOrder);
                view.displayOrderAddedBanner(newOrder);
                isValid = true; // Exit the loop if the order is successfully added
            } catch (OrderDAOException e) {
                view.displayErrorMessage(e.getMessage()); // Display the error message and loop again
            }
        }
    }

    private void editOrder() throws OrderDAOException {
        boolean validOrderFound = false;
        while (!validOrderFound) {
            try {
                view.editOrderBanner();
                int orderId = view.getOrderNumber();
                LocalDate date = view.getDate();
                OrderDTO order = orderService.getOrder(orderId, date);

                if (order == null) {
                    view.displayErrorMessage("No order found with the given order number and date. Please try again.");
                    continue;
                }

                OrderDTO editedOrder = view.editOrderInfo(order, orderService.getValidStates(), orderService.getValidProducts());
                orderService.editOrder(editedOrder, date);
                view.displayOrderEditedBanner(editedOrder);
                validOrderFound = true; // Exit the loop if a valid order is found and edited.
            } catch (Exception e) {
                view.displayErrorMessage(e.getMessage());
            }
        }
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
