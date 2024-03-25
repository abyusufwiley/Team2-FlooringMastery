package com.sg.FlooringMastery.UI;

import java.math.BigDecimal;
import java.util.List;

import com.sg.FlooringMastery.DTO.OrderDTO;

public class View {
    private UserIO io = new UserIOImpl();

    public View(UserIO io) {
        this.io = io;
    }

    /*
     * 
     *   * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
  * <<Flooring Program>>
  * 1. Display Orders
  * 2. Add an Order
  * 3. Edit an Order
  * 4. Remove an Order
  * 5. Export All Data
  * 6. Quit
  *
  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    public int printMenuAndGetSelection() {
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

    public OrderDTO getNewOrderInfo() {
        int orderNumber = io.readInt("Please enter Order Number");
        String customerName = io.readString("Please enter Customer Name");
        String state = io.readString("Please enter State");
        String productType = io.readString("Please enter Product Type");
        double area = io.readDouble("Please enter Area");

        OrderDTO currentOrder = new OrderDTO(orderNumber);
        currentOrder.setCustomerName(customerName);
        currentOrder.setState(state);
        currentOrder.setProductType(productType);
        currentOrder.setArea(BigDecimal.valueOf(area));

        return currentOrder;
    }

    public OrderDTO editOrderInfo(OrderDTO order) {
        String customerName = io.readString("Please enter Customer Name");
        String state = io.readString("Please enter State");
        String productType = io.readString("Please enter Product Type");
        double area = io.readDouble("Please enter Area");

        order.setCustomerName(customerName);
        order.setState(state);
        order.setProductType(productType);
        order.setArea(BigDecimal.valueOf(area));

        return order;
    }

    public int getOrderNumber() {
        return io.readInt("Please enter Order Number");
    }

    public String getCustomerName() {
        return io.readString("Please enter Customer Name");
    }

    public void displayOrder(OrderDTO order) {
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
