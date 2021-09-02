package com.demo.shop.product.domain;

import javax.persistence.*;

@Entity
@Table
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;
    private String name;
    private String company;
    private double pvpPrice;
    private double costPrice;
    private String pvpCode;
    private String costCode;

    public Product(String name, String company, double pvpPrice, double costPrice, String pvpCode, String costCode) {
        this.name = name;
        this.company = company;
        this.pvpPrice = pvpPrice;
        this.costPrice = costPrice;
        this.pvpCode = pvpCode;
        this.costCode = costCode;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getPvpPrice() {
        return pvpPrice;
    }

    public void setPvpPrice(double pvpPrice) {
        this.pvpPrice = pvpPrice;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public String getPvpCode() {
        return pvpCode;
    }

    public void setPvpCode(String pvpCode) {
        this.pvpCode = pvpCode;
    }

    public String getCostCode() {
        return costCode;
    }

    public void setCostCode(String costCode) {
        this.costCode = costCode;
    }
}
