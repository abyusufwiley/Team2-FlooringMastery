package com.sg.FlooringMastery.DAO;

import com.sg.FlooringMastery.DTO.TaxDTO;

import java.util.List;

public interface TaxDAO {
    List<TaxDTO> getAllTaxes();
    TaxDTO getTax(String state);
}
