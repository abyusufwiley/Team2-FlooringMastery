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
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

import com.sg.FlooringMastery.DTO.OrderDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderDAOImpl implements OrderDAO{
    private String ORDER_FILE;
    private final String DELIMITER = ",";
    private Map<Integer, OrderDTO> orders = new HashMap<>();
    private final String ORDERS_DIRECTORY = "./Orders";



    public OrderDAOImpl(){
    }

    public OrderDAOImpl(String orderTextFile){   // Constructor allowing for a file path.
        ORDER_FILE = orderTextFile;
    }

    public String buildFilename(LocalDate date){  // Builds filename for an order file based on a given date
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMddyyyy"); // Date format for the filename
        return ORDERS_DIRECTORY + "/Orders_" + date.format(dateFormat) + ".txt"; // Returns the filename
    }



    @Override
    public OrderDTO getOrder(int orderId, LocalDate date) throws OrderDAOException {
        //get order by order id and name
        String orderFile = buildFilename(date);
        loadOrder(orderFile); //Builds the filename and loads the order
        return orders.get(orderId);
    }



    private void writeOrder(String orderFile) throws OrderDAOException { // Writes the order to the file
        File file = new File(orderFile); // Creates a new file
        if (!file.exists()) { // If the file does not exist
            try {
                file.createNewFile(); // Create a new file
            } catch (IOException e) {
                throw new OrderDAOException("Could not create order file.", e); // Throw an exception if the file cannot be created
            }
        }

        try (PrintWriter out = new PrintWriter(new FileWriter(file))) { // Try to write to the file
            for (OrderDTO currentOrder : orders.values()) { // For each order in the orders map
                String orderAsText = marshallItem(currentOrder); // Marshall the order
                out.println(orderAsText); // Print the order to the file
                out.flush(); // Flush the output
            }
        } catch (IOException e) {
            throw new OrderDAOException("Could not save order data.", e); // Throw an exception if the order data cannot be saved
        }
    }

    private void loadOrder(String orderFile) throws OrderDAOException{ // Loads the order from the file
        orders.clear(); // Clears the orders map
        Scanner scanner; // Creates a new scanner
        LocalDate fileDate = getDateFromFilename(orderFile); // Gets the date from the filename
        File file = new File(orderFile); // Creates a new file
        if (!file.exists()){ // If the file does not exist
            return; // Return nothing
        }

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(file))); // Try to read from the file
        } catch (FileNotFoundException e) {
            throw new OrderDAOException(
                    "-_- Could not load item data into memory.", e); // Throw an exception if the item data cannot be loaded into memory
        }

        String currentLine; // Creates a new string
        OrderDTO currentOrder; // Creates a new order

        while (scanner.hasNextLine()){ // While the scanner has a next line
            currentLine = scanner.nextLine(); // Set the current line to the next line
            currentOrder = unmarshallItem(currentLine); // Unmarshall the current line
            currentOrder.setDate(fileDate); // Set the date of the current order
            orders.put(currentOrder.getOrderNumber(), currentOrder); // Put the current order in the orders map
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
        orderAsText += order.getDate().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        return orderAsText;
    }
    public LocalDate getDateFromFilename(String filename){ // Gets the date from the filename
        // Extract the date part of the filename starting from characters after _ to characters before .txt
        String datePart = filename.substring(filename.lastIndexOf('_') + 1, filename.lastIndexOf('.'));
        // Parse the date part to LocalDate
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMddyyyy");
        try {
            return LocalDate.parse(datePart, dateFormatter); // Return the parsed date
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Failed to parse the date from filename: " + filename, e); // Throw an exception if the date cannot be parsed
        }
    }

    public List<OrderDTO> getOrdersByDate(LocalDate date) throws OrderDAOException {
        String orderFile = buildFilename(date); //Builds the filename
        loadOrder(orderFile); // Loads the order
        return orders.values().stream() // Starts a stream of the values in the orders map
                .filter(order -> order.getState().equals(date)) //Filters the orders by date
                .collect(Collectors.toList()); //Collects the orders into a list
    }

    @Override
    public List<OrderDTO> getAllOrders() throws OrderDAOException {
        // Build filename for today's date
        String orderFile = buildFilename(LocalDate.now());
        loadOrder(orderFile); //Load orders from the file
        return new ArrayList<>(orders.values()); // Return a new ArrayList created from the values in the orders map.

    }

    /**
     * Adds a new order to the system, by generating a filename based on the order's date,
     * clearing any currently loaded orders, loading existing orders from
     * the specified file, adding the new order to the collection, writing the updated collection back
     * to the file, and finally returning the added order.
     */
    @Override
    public OrderDTO addOrder(OrderDTO order) throws OrderDAOException {
        String orderFile = buildFilename(order.getDate());

        orders.clear();
        loadOrder(orderFile);
        orders.put(order.getOrderNumber(), order);
        writeOrder(orderFile);
        return order;
    }

    @Override
    public OrderDTO editOrder(OrderDTO order, LocalDate date) throws OrderDAOException {
        String orderFile = buildFilename(order.getDate());
        loadOrder(orderFile);
        orders.put(order.getOrderNumber(), order);
        writeOrder(orderFile);
        return order;
    }
    /**
     * Removes an order based on its ID and date. After determining the filename from the  date and
     * loading the relevant orders, the specified order is removed from the collection. The updated collection
     * is then written back to the file, and it returns it
     */
    @Override
    public OrderDTO removeOrder(int orderId, LocalDate date) throws OrderDAOException {
        String orderFile = buildFilename(date);
        loadOrder(orderFile);
        OrderDTO removedOrder = orders.remove(orderId);
        writeOrder(orderFile);
        return removedOrder;
    }
    /**
     * The method below exports all data from the orders directory to a backup file. It first makes sure that the destination
     * directory exists, then iterates through each file in the orders directory that matches the naming
     * pattern of order files, reading each line and writing it to the export file. If any step fails,
     * an appropriate exception is thrown
     */
    @Override
    public void exportAllData() throws OrderDAOException {
        File srcFolder = new File("./Orders");
        File exportFile = new File("./backup/DataExport.txt");

        exportFile.getParentFile().mkdirs(); //mkdirs creates a directory if it does not exist

        PrintWriter out = null;

        try {
            out = new PrintWriter(new FileWriter(exportFile));
            File[] listOfFiles = srcFolder.listFiles((directory, name) -> name.startsWith("Orders_") && name.endsWith(".txt"));
            if (listOfFiles == null) {
                throw new OrderDAOException("Could not list files in source directory.");
            }
            for (File file : listOfFiles) {
                try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(file)))) {
                    while (scanner.hasNextLine()) {
                        String currentLine = scanner.nextLine();
                        out.println(currentLine);
                    }
                } catch (FileNotFoundException e) {
                    throw new OrderDAOException("Could not read file: " + file.getName(), e);
                }
            }
        } catch (IOException e) {
            throw new OrderDAOException("Could not create export file.", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * Ensures the orders directory exists, creating it using mkdir if it doesn't. This makes
     * sure that there is a directory to read from or write to
     */
    private void ensureOrdersDirectory() {
        File ordersDir = new File(ORDERS_DIRECTORY);
        if (!ordersDir.exists()) {
            ordersDir.mkdirs();
        }
    }



}
