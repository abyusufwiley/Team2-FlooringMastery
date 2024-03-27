package com.sg.FlooringMastery.Test.ServiceTest;

import com.sg.FlooringMastery.DAO.TaxDAO;
import com.sg.FlooringMastery.DTO.TaxDTO;
import java.math.BigDecimal;
import java.util.List;

public class TaxDAOStubImpl implements TaxDAO {
    public TaxDTO onlyTax;

    public TaxDAOStubImpl() {
        this.onlyTax = new TaxDTO("OH", "Ohio", BigDecimal.valueOf(6.25));
    }

    public TaxDAOStubImpl(TaxDTO testTax) {
        this.onlyTax = testTax;
    }

    public TaxDTO getTax(String state) {
        return state.equals(this.onlyTax.getState()) ? this.onlyTax : null;
    }

    public List<TaxDTO> getAllTaxes() {
        List<TaxDTO> taxList = List.of(this.onlyTax);
        return taxList;
    }
}
