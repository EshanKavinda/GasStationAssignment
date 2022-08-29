package com.eshanperera.allocationservice.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Reservation")
public class Reservation {

    @Id
    private String id;
    private String orderId;
    private int fuelType;
    private double amount;
    private String allocatedDate;
    private int status;

    public Reservation(String orderId, int fuelType, double amount, String allocatedDate, int status) {
        this.orderId = orderId;
        this.fuelType = fuelType;
        this.amount = amount;
        this.allocatedDate = allocatedDate;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getFuelType() {
        return fuelType;
    }

    public void setFuelType(int fuelType) {
        this.fuelType = fuelType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAllocatedDate() {
        return allocatedDate;
    }

    public void setAllocatedDate(String allocatedDate) {
        this.allocatedDate = allocatedDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
