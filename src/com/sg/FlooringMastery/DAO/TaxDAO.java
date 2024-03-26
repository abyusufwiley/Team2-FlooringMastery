package com.sg.FlooringMastery.DAO;

import com.sg.FlooringMastery.DTO.TaxDTO;

import java.util.List;

public interface TaxDAO {
    /**
     * The ProductDAO interface defines the variable operations on Tax data,
     * allowing for retrieval of Tax information.
     */
    List<TaxDTO> getAllTaxes();
    TaxDTO getTax(String state);
}
