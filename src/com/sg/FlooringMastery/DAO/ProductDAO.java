package com.sg.FlooringMastery.DAO;

import com.sg.FlooringMastery.DTO.ProductDTO;

import java.util.List;

public interface ProductDAO {
    List<ProductDTO> getAllProducts();

    ProductDTO getProduct(String productType);
}
