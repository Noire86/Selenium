package ru.ibs.myframework.pageobjects.dns.models;

import java.util.List;

public class Product {

    private String name;
    private String code;
    private int price;
    private int warranty;
    private List<String> basicSpecs;

    public Product(String name, String code, int price, int warranty, List<String> basicSpecs) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.warranty = warranty;
        this.basicSpecs = basicSpecs;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    public List<String> getBasicSpecs() {
        return basicSpecs;
    }

    public void setBasicSpecs(List<String> basicSpecs) {
        this.basicSpecs = basicSpecs;
    }
}
