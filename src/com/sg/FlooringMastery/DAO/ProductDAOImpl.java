package com.sg.FlooringMastery.DAO;

import com.sg.FlooringMastery.DTO.ProductDTO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

// ProductDAOImpl implements ProductDAO
// only reads from the Products.txt file. Does not write to it.

public class ProductDAOImpl implements ProductDAO{
    private final String PRODUCTS_FILE;
    private final String DELIMITER = ",";
    private Map<String, ProductDTO> products = new HashMap<>();

    // Constructor
    public ProductDAOImpl(){
        PRODUCTS_FILE = "Data\\Products.txt";
    }

    // Constructor
    public ProductDAOImpl(String productsTextFile){
        PRODUCTS_FILE = productsTextFile;
    }

    // loadProducts() reads from the Products.txt file and populates the products map  with the data
    public void loadProducts(){
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(PRODUCTS_FILE)));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                ProductDTO product = unmarshallProduct(line);
                products.put(product.getProductType(), product);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not load products data.");
        }

    }
    private ProductDTO unmarshallProduct(String productAsText){
        String[] productTokens = productAsText.split(DELIMITER);
        String productType = productTokens[0];
        BigDecimal costPerSquareFoot = new BigDecimal(productTokens[1]);
        BigDecimal laborCostPerSquareFoot = new BigDecimal(productTokens[2]);
        return new ProductDTO(productType, costPerSquareFoot, laborCostPerSquareFoot);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        loadProducts();
        return new ArrayList<>(products.values());
    }

    @Override
    public ProductDTO getProduct(String productType) {
        loadProducts();
        return products.get(productType);
    }
}
