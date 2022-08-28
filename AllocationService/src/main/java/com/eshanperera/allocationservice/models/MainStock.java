package com.eshanperera.allocationservice.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("MainStock")
public class MainStock {

    @Id
    private int id;
    private String fuelType;
    private double availableQuantity;
    private int status;

    public MainStock(int id, String fuelType, double availableQuantity, int status) {
        this.id = id;
        this.fuelType = fuelType;
        this.availableQuantity = availableQuantity;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(double availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
