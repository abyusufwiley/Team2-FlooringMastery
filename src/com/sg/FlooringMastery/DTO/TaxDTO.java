package com.sg.FlooringMastery.DTO;

import java.math.BigDecimal;

public class TaxDTO {
    private String state;
    private String stateName;
    private BigDecimal taxRate;

    public TaxDTO(String state, String stateName, BigDecimal taxRate) {
        this.state = state;
        this.taxRate = taxRate;
        this.stateName = stateName;
    }

    public String getState() {
        return state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public String getStateName() {
        return stateName;
    }
}
