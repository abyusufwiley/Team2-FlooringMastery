package com.sg.FlooringMastery.UI;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.sg.FlooringMastery.DTO.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class View {
    private UserIO io = new UserIOImpl(); // UserIO object to handle input/output operations
@Autowired
    public View(UserIO io) {
        this.io = io;
    } // Constructor that initializes the UserIO object


    public int printMenuAndGetSelection() {
    // Displays the main menu and gets the user's selection
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print("<<Flooring Program>>");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Export All Data");
        io.print("6. Quit");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public void displayOrdersBanner() {
        io.print("=== Display Orders ===");
    }

    public void addOrderBanner() {
        io.print("=== Add an Order ===");
    }

    public void editOrderBanner() {
        io.print("=== Edit an Order ===");
    }

    public void removeOrderBanner() {
        io.print("=== Remove an Order ===");
    }

    public void exportAllDataBanner() {
        io.print("=== Export All Data ===");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displayOrders(List<OrderDTO> orders) {
    // Displays a list of orders by iterating through the list and printing each order
        for (OrderDTO currentOrder : orders) {
            io.print(currentOrder.getOrderNumber() + ": "
                    + currentOrder.getCustomerName() + " "
                    + currentOrder.getState() + " "
                    + currentOrder.getTaxRate() + " "
                    + currentOrder.getProductType() + " "
                    + currentOrder.getArea() + " "
                    + currentOrder.getCostPerSquareFoot() + " "
                    + currentOrder.getLaborCostPerSquareFoot() + " "
                    + currentOrder.getMaterialCost() + " "
                    + currentOrder.getLaborCost() + " "
                    + currentOrder.getTax() + " "
                    + currentOrder.getTotal());
        }
        io.readString("Please hit enter to continue.");
    }

    public OrderDTO getNewOrderInfo(List<String> validStates, List<String> validProducts) {
        //Gets the information for a new order by getting the order info from users
        int orderNumber = io.readInt("Please enter Order Number");
        LocalDate date = io.readLocalDate("Please enter Date in the format YYYY-MM-DD");
        String customerName = io.readString("Please enter Customer Name");
        String state = "";
        boolean stateValid = false;

        // State Input Validation
        while (!stateValid) {
            state = io.readString("Please enter State:");
            if (validStates.contains(state)) {
                stateValid = true;
            } else {
                io.print("Invalid state. Please enter a valid state.");
            }
        }

        String productType = "";
        boolean productValid = false;
        // Product Type Input Validation
        while (!productValid) {
            productType = io.readString("Please enter Product Type:");
            if (validProducts.contains(productType)) {
                productValid = true;
            } else {
                io.print("Invalid product type. Please enter a valid product type.");
            }
        }

        double area = io.readDouble("Please enter Area");

        OrderDTO currentOrder = new OrderDTO(orderNumber);
        currentOrder.setDate(date);
        currentOrder.setCustomerName(customerName);
        currentOrder.setState(state);
        currentOrder.setProductType(productType);
        currentOrder.setArea(BigDecimal.valueOf(area));

        return currentOrder;
    }
    public OrderDTO editOrderInfo(OrderDTO order, List<String> validStates, List<String> validProducts) {
        // Edits an existing order by getting the order informations
        String customerName = io.readString("Please enter Customer Name (" + order.getCustomerName() + "):");
        if (customerName.isEmpty()) {
            order.setCustomerName(order.getCustomerName());
        } else {
            order.setCustomerName(customerName);
        }
        // State Validation
        boolean stateValid = false;
        String state = "";
        while (!stateValid) {
            state = io.readString("Please enter State: [" + order.getState() + "]:");
            if (state.isEmpty() || validStates.contains(state)) {
                stateValid = true;
                if (!state.isEmpty()) {
                    order.setState(state);
                }
            } else {
                io.print("Invalid state. Please enter a valid state.");
            }
        }

        // Product Type Validation
        boolean productValid = false;
        String productType = "";
        while (!productValid) {
            productType = io.readString("Please enter Product Type: [" + order.getProductType() + "]:");
            if (productType.isEmpty() || validProducts.contains(productType)) {
                productValid = true;
                if (!productType.isEmpty()) {
                    order.setProductType(productType);
                }
            } else {
                io.print("Invalid product type. Please enter a valid product type.");
            }
        }

        double area = io.readDouble("Please enter Area (" + order.getArea() + "):");
        if (area != 0) {
            order.setArea(BigDecimal.valueOf(area));
        }

        return order;
    }

    public int getOrderNumber() {
        return io.readInt("Please enter Order Number");
    }

    public LocalDate getDate() {
        String userDateInput =  io.readString("Please enter Date in the format YYYY-MM-DD");
        return LocalDate.parse(userDateInput);
    }

    public void displayOrder(OrderDTO order) {
    //Displays orders
        if (order != null) {
            io.print(order.getOrderNumber() + ": "
                    + order.getCustomerName() + " "
                    + order.getState() + " "
                    + order.getTaxRate() + " "
                    + order.getProductType() + " "
                    + order.getArea() + " "
                    + order.getCostPerSquareFoot() + " "
                    + order.getLaborCostPerSquareFoot() + " "
                    + order.getMaterialCost() + " "
                    + order.getLaborCost() + " "
                    + order.getTax() + " "
                    + order.getTotal());
        } else {
            io.print("No such order.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayOrderRemovedBanner(OrderDTO order) {
        if (order != null) {
            io.print("Order Number " + order.getOrderNumber() + " has been removed.");
        } else {
            io.print("No such order.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayOrderEditedBanner(OrderDTO order) {
        if (order != null) {
            io.print("Order Number " + order.getOrderNumber() + " has been edited.");
        } else {
            io.print("No such order.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayOrderAddedBanner(OrderDTO order) {
        if (order != null) {
            io.print("Order Number " + order.getOrderNumber() + " has been added.");
        } else {
            io.print("No such order.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayExportAllDataBanner() {
        io.print("All data has been exported.");
        io.readString("Please hit enter to continue.");
    }

    public void displayUnknownOrderBanner() {
        io.print("Unknown Order!!!");
    }


}
