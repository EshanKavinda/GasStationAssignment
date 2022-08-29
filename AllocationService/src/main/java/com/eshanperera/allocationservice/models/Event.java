package com.eshanperera.allocationservice.models;

public class Event {

    private String orderId;
    private String type;
    private String data;
    private String result;
    private String from;

    public Event() {
        super();
    }

    public Event(String orderId, String type, String data, String result, String from) {
        this.orderId = orderId;
        this.type = type;
        this.data = data;
        this.result = result;
        this.from = from;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
