package com.sg.FlooringMastery.DAO;

import java.util.List;

import com.sg.FlooringMastery.DTO.ProductDTO;

public interface ProductDAO {
    public ProductDTO getProduct(String productType) throws ProductDAOException;
    public List<ProductDTO> getAllProducts() throws ProductDAOException;

}
