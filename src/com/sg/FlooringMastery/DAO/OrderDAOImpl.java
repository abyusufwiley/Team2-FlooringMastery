package com.sg.FlooringMastery.DAO;

import java.io.BufferedReader;
import java.io.File;
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
    private String ORDER_FILE;
    private final String DELIMITER = ",";
    private Map<Integer, OrderDTO> orders = new HashMap<>();


    
    public OrderDAOImpl(){      
    }

    public OrderDAOImpl(String orderTextFile){
        ORDER_FILE = orderTextFile;
    }

    public String buildFilename(LocalDate date){
        return "Orders-" + date + ".txt";
    }



    @Override
    public OrderDTO getOrder(int orderId, LocalDate date) throws OrderDAOException {
        //get order by order id and name
        String orderFile = buildFilename(LocalDate.now());
        loadOrder(orderFile);
        return orders.get(orderId);
    }


   
    private void writeOrder(String orderFile) throws OrderDAOException{
        PrintWriter out;
        //file may not exist if it doesn't create an empty file to write to
        if (orders.isEmpty()){
            try {
                out = new PrintWriter(new FileWriter(orderFile));
            } catch (IOException e) {
                throw new OrderDAOException("Could not save order data.", e);
            }
            out.close();
        }

        try {
            out = new PrintWriter(new FileWriter(orderFile));
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

    private void loadOrder(String orderFile) throws OrderDAOException{
        Scanner scanner;

        File file = new File(orderFile);
        if (!file.exists()){
            return;
        }

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(file)));
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


    @Override
    public List<OrderDTO> getAllOrders() throws OrderDAOException {
        String orderFile = buildFilename(LocalDate.now());
        loadOrder(orderFile);
        return new ArrayList<>(orders.values());
    }

    @Override
    public OrderDTO addOrder(OrderDTO order) throws OrderDAOException {
        String orderFile = buildFilename(LocalDate.now());
        loadOrder(orderFile);
        orders.put(order.getOrderNumber(), order);
        writeOrder(orderFile);
        return order;
    }

    @Override
    public OrderDTO editOrder(OrderDTO order, LocalDate date) throws OrderDAOException {
        String orderFile = buildFilename(date);
        loadOrder(orderFile);
        orders.put(order.getOrderNumber(), order);
        writeOrder(orderFile);
        return order;
    }

    @Override
    public OrderDTO removeOrder(int orderId, LocalDate date) throws OrderDAOException {
        OrderDTO order = orders.remove(orderId);
        return order;
    }

    @Override
    public void exportAllData() throws OrderDAOException {
        
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter("DataExport.txt"));
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

     private LocalDate getDateFromFilename(String filename){
        String date = filename.substring(7, 17);
        return LocalDate.parse(date);
    }


}
