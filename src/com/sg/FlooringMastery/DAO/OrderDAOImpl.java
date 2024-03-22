package com.sg.FlooringMastery.DAO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.sg.FlooringMastery.DTO.OrderDTO;

public class OrderDAOImpl implements OrderDAO{
    private final String ORDER_FILE = "Orders.txt";
    private final String DELIMITER = ",";
    private Map<Integer, OrderDTO> orders = new HashMap<>();



    @Override
    public OrderDTO getOrder(int orderId, String name) throws OrderDAOException {
        //get order by order id and name
        loadOrder();
        
        throw new UnsupportedOperationException("");
    }



    private void loadOrder() throws OrderDAOException{
        Scanner scanner;

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

        throw new UnsupportedOperationException("Unimplemented method 'loadOrder'");
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

    private void writeOrder() throws OrderDAOException{
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ORDER_FILE));
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



    @Override
    public List<OrderDTO> getAllOrders() throws OrderDAOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllOrders'");
    }

    @Override
    public OrderDTO addOrder(OrderDTO order) throws OrderDAOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addOrder'");
    }

    @Override
    public OrderDTO editOrder(int orderId, String name) throws OrderDAOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editOrder'");
    }

    @Override
    public OrderDTO removeOrder(int orderId, LocalDate date) throws OrderDAOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeOrder'");
    }

}
