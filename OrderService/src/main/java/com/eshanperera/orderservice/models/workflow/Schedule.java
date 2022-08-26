package com.eshanperera.orderservice.models.workflow;

public class Schedule {

    private String status;
    private String processdate;
    private String arrival_date;

    public Schedule(String status, String processdate, String arrival_date) {
        this.status = status;
        this.processdate = processdate;
        this.arrival_date = arrival_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProcessdate() {
        return processdate;
    }

    public void setProcessdate(String processdate) {
        this.processdate = processdate;
    }

    public String getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(String arrival_date) {
        this.arrival_date = arrival_date;
    }
}
