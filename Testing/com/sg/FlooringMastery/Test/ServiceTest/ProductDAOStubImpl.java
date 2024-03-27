package com.sg.FlooringMastery.Test.ServiceTest;

import com.sg.FlooringMastery.DAO.ProductDAO;
import com.sg.FlooringMastery.DAO.ProductDAOException;
import com.sg.FlooringMastery.DTO.ProductDTO;
import java.math.BigDecimal;
import java.util.List;

public class ProductDAOStubImpl implements ProductDAO {
    public ProductDTO onlyProduct;

    public ProductDAOStubImpl() {
        this.onlyProduct = new ProductDTO("Wood", BigDecimal.valueOf(5.15), BigDecimal.valueOf(4.75));
    }

    public ProductDAOStubImpl(ProductDTO testProduct) {
        this.onlyProduct = testProduct;
    }

    public ProductDTO getProduct(String productType) throws ProductDAOException {
        return productType.equals(this.onlyProduct.getProductType()) ? this.onlyProduct : null;
    }

    public List<ProductDTO> getAllProducts() throws ProductDAOException {
        List<ProductDTO> productList = List.of(this.onlyProduct);
        return productList;
    }
}
