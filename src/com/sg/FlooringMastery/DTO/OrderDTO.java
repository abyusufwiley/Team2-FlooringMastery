package com.sg.FlooringMastery.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class OrderDTO {
    private int orderNumber;
    private String customerName;
    private String state;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal area;
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal total;
    private LocalDate date;

    

    public OrderDTO(int orderNumber, String customerName, LocalDate date) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.date = date;
    }

    public OrderDTO(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }


    public String getCustomerName() {
        return customerName;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area2) {
        this.area = area2;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDTO orderDTO)) return false;
        return orderNumber == orderDTO.orderNumber && Objects.equals(customerName, orderDTO.customerName) && Objects.equals(state, orderDTO.state) && Objects.equals(taxRate, orderDTO.taxRate) && Objects.equals(productType, orderDTO.productType) && Objects.equals(area, orderDTO.area) && Objects.equals(costPerSquareFoot, orderDTO.costPerSquareFoot) && Objects.equals(laborCostPerSquareFoot, orderDTO.laborCostPerSquareFoot) && Objects.equals(materialCost, orderDTO.materialCost) && Objects.equals(laborCost, orderDTO.laborCost) && Objects.equals(tax, orderDTO.tax) && Objects.equals(total, orderDTO.total) && Objects.equals(date, orderDTO.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, customerName, state, taxRate, productType, area, costPerSquareFoot, laborCostPerSquareFoot, materialCost, laborCost, tax, total, date);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderNumber=" + orderNumber +
                ", customerName='" + customerName + '\'' +
                ", state='" + state + '\'' +
                ", taxRate=" + taxRate +
                ", productType='" + productType + '\'' +
                ", area=" + area +
                ", costPerSquareFoot=" + costPerSquareFoot +
                ", laborCostPerSquareFoot=" + laborCostPerSquareFoot +
                ", materialCost=" + materialCost +
                ", laborCost=" + laborCost +
                ", tax=" + tax +
                ", total=" + total +
                ", date=" + date +
                '}';
    }
}
