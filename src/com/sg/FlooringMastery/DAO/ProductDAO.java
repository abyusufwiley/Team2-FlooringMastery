package com.sg.FlooringMastery.DAO;

import java.util.List;

import com.sg.FlooringMastery.DTO.ProductDTO;

public interface ProductDAO {
    /**
     * The ProductDAO interface defines the variable operations on Product data,
     * allowing for retrieval of product information.
     */
    public ProductDTO getProduct(String productType) throws ProductDAOException;
    public List<ProductDTO> getAllProducts() throws ProductDAOException;

}
