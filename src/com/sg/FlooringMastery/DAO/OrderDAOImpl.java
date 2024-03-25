package com.sg.FlooringMastery.DAO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import com.sg.FlooringMastery.DTO.OrderDTO;

public class OrderDAOImpl implements OrderDAO{
    private final String ORDER_FILE;
    private final String DELIMITER = ",";
    private Map<Integer, OrderDTO> orders = new HashMap<>();

    
    public OrderDAOImpl(){
        ORDER_FILE = "Orders\\Orders.txt";       
    }

    // public OrderDAOImpl(){    
    // }

    // Constructor
    public OrderDAOImpl(String orderTextFile){
        ORDER_FILE = orderTextFile;
    }



    @Override
    public OrderDTO getOrder(int orderId, String name) throws OrderDAOException {
        //get order by order id and name
        loadOrder();
        return orders.get(orderId);
    }


   
    private void writeOrder() throws OrderDAOException{
        PrintWriter out;

        try {
            //LocalDate date = LocalDate.now();
            out = new PrintWriter(new FileWriter("Orders_" + LocalDate.now() + ".txt"));
        } catch (IOException e) {
            throw new OrderDAOException("Could not save order data.", e);
        }

        String orderAsText;
        List<OrderDTO> orderList = this.getAllOrders();
        for (OrderDTO currentOrder : orderList){
            orderAsText = marshallItem(currentOrder);
            out.println(orderAsText);
            out.flush();
        }
        out.close();
    }

    private void loadOrder() throws OrderDAOException{
        Scanner scanner;
        //file may not exist if it doesn't create an empty file

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(ORDER_FILE)));
        } catch (FileNotFoundException e) {
            throw new OrderDAOException(
                    "-_- Could not load item data into memory.", e);
        }

        String currentLine;
        OrderDTO currentOrder;

        while (scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentOrder = unmarshallItem(currentLine);
            orders.put(currentOrder.getOrderNumber(), currentOrder);
        }

        scanner.close();
    }

    private OrderDTO unmarshallItem (String itemAsText){

        String[] orderTokens = itemAsText.split(DELIMITER);
        int orderNumber = Integer.parseInt(orderTokens[0]);
        OrderDTO order = new OrderDTO(orderNumber);
        order.setCustomerName(orderTokens[1]);
        order.setState(orderTokens[2]);
        order.setTaxRate(new BigDecimal(orderTokens[3]));
        order.setProductType(orderTokens[4]);
        order.setArea(new BigDecimal(orderTokens[5]));
        order.setCostPerSquareFoot(new BigDecimal(orderTokens[6]));
        order.setLaborCostPerSquareFoot(new BigDecimal(orderTokens[7]));
        order.setMaterialCost(new BigDecimal(orderTokens[8]));
        order.setLaborCost(new BigDecimal(orderTokens[9]));
        order.setTax(new BigDecimal(orderTokens[10]));
        order.setTotal(new BigDecimal(orderTokens[11]));
        
        return order;
    }

    private String marshallItem(OrderDTO order){
        String orderAsText = order.getOrderNumber() + DELIMITER;
        orderAsText += order.getCustomerName() + DELIMITER;
        orderAsText += order.getState() + DELIMITER;
        orderAsText += order.getTaxRate() + DELIMITER;
        orderAsText += order.getProductType() + DELIMITER;
        orderAsText += order.getArea() + DELIMITER;
        orderAsText += order.getCostPerSquareFoot() + DELIMITER;
        orderAsText += order.getLaborCostPerSquareFoot() + DELIMITER;
        orderAsText += order.getMaterialCost() + DELIMITER;
        orderAsText += order.getLaborCost() + DELIMITER;
        orderAsText += order.getTax() + DELIMITER;
        orderAsText += order.getTotal() + DELIMITER;
        
        return orderAsText;
    }

    // private void writeOrder() throws OrderDAOException{
    //     PrintWriter out;

    //     try {
    //         out = new PrintWriter(new FileWriter(ORDER_FILE));
    //     } catch (IOException e) {
    //         throw new OrderDAOException("Could not save order data.", e);
    //     }

    //     String orderAsText;
    //     List<OrderDTO> orderList = this.getAllOrders();
    //     for (OrderDTO currentOrder : orderList){
    //         orderAsText = marshallItem(currentOrder);
    //         out.println(orderAsText);
    //         out.flush();
    //     }
    //     out.close();
    // }



    @Override
    public List<OrderDTO> getAllOrders() throws OrderDAOException {
        loadOrder();
        return new ArrayList<>(orders.values());
    }

    @Override
    public OrderDTO addOrder(OrderDTO order) throws OrderDAOException {
        loadOrder();
        orders.put(order.getOrderNumber(), order);
        writeOrder();
        return order;
    }

    @Override
    public OrderDTO editOrder(OrderDTO order) throws OrderDAOException {
        loadOrder();
        orders.put(order.getOrderNumber(), order);
        writeOrder();
        return order;
    }

    @Override
    public OrderDTO removeOrder(int orderId, LocalDate date) throws OrderDAOException {
        loadOrder();
        OrderDTO order = orders.remove(orderId);
        writeOrder();
        return order;
    }

    @Override
    public void exportAllData() throws OrderDAOException {
        // Load all the current orders from a file into memory
        loadOrder();
        // Declare a PrintWriter object to write formatted data to an output stream
        PrintWriter out;
        try {
            // Initialize the PrintWriter object with a FileWriter for the file "DataExport.txt"
            // If the file doesn't exist, FileWriter will create it
            out = new PrintWriter(new FileWriter("DataExport.txt"));
        } catch (IOException e) {
            throw new OrderDAOException("Could not save order data.", e);
        }

        // Declare a string to hold the order data as text
        String orderAsText;
        // Retrieve all the orders that were loaded by the loadOrder() method
        List<OrderDTO> orderList = this.getAllOrders();
        // Iterate over each order in the order list
        for (OrderDTO currentOrder : orderList){
            orderAsText = marshallItem(currentOrder);
            out.println(orderAsText);
            out.flush();
        }
        out.close();

     }

}
