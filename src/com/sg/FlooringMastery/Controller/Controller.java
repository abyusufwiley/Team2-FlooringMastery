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
public class Controller { // Controller class orchestrates the flow between the view and the service layer.

    private View view ;
    private ServiceLayer orderService;
@Autowired //Used autowired to inject spring dependecies
    public Controller(View view, ServiceLayer serviceLayer) {
        this.view = view;
        this.orderService = serviceLayer;
    }

    public Controller() { //default constructor for when Spring DI is not used
    }


    public void run() throws OrderDAOException { // The main loop for user interaction, displays the menu and handles user input
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

    private int getMenuSelection() { // Retrieves the user's menu selection
        return view.printMenuAndGetSelection();
    }

    // Displays orders from a specific date
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

    private void addOrder() throws OrderDAOException {  // Allows the user to a new order based on dates
                                                        //Loops if user puts invalid information in order.
        boolean isValid = false;
        while (!isValid) {
            try {
                view.addOrderBanner();
                List<String> validStates = orderService.getValidStates();
                List<String> validProducts = orderService.getValidProducts();
                OrderDTO newOrder = view.getNewOrderInfo(validStates, validProducts);
                orderService.addOrder(newOrder);
                view.displayOrderAddedBanner(newOrder);
                isValid = true;
            } catch (OrderDAOException e) {
                view.displayErrorMessage(e.getMessage());
            }
        }
    }

    private void editOrder() throws OrderDAOException {    // Allows the user to edit an existing order
        boolean validOrderFound = false;
        while (!validOrderFound) {
            //Users must put in valid information
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
                validOrderFound = true;
            } catch (Exception e) {
                view.displayErrorMessage(e.getMessage());
            }
        }
    }


    private void removeOrder() throws OrderDAOException { // Removes orders from file
        view.removeOrderBanner();
        int orderId = view.getOrderNumber();
        LocalDate date = view.getDate();
        OrderDTO order = orderService.getOrder(orderId, date);
        orderService.removeOrder(orderId, order.getDate());
        view.displayOrdersBanner();
    }

    private void exportAllData() throws OrderDAOException { //Exports all orders into an external file
        view.exportAllDataBanner();
        orderService.exportAllData();
    }

    private void unknownCommand() { //Displays unknown command message
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() { //Displays exit message
        view.displayExitBanner();
    }

    private void errorMessage(String errorMsg) { //Displays error message
        view.displayErrorMessage(errorMsg);
    }

}
