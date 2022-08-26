package com.eshanperera.orderservice.models.workflow;

public class WorkFlowStatus {

    private Created created;
    private Allocation allocation;
    private Schedule schedule;
    private Dispatch dispatch;
    private OrderReceived orderReceived;

    public WorkFlowStatus(Created created, Allocation allocation, Schedule schedule, Dispatch dispatch, OrderReceived orderReceived) {
        this.created = created;
        this.allocation = allocation;
        this.schedule = schedule;
        this.dispatch = dispatch;
        this.orderReceived = orderReceived;
    }

    public Created getCreated() {
        return created;
    }

    public void setCreated(Created created) {
        this.created = created;
    }

    public Allocation getAllocation() {
        return allocation;
    }

    public void setAllocation(Allocation allocation) {
        this.allocation = allocation;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Dispatch getDispatch() {
        return dispatch;
    }

    public void setDispatch(Dispatch dispatch) {
        this.dispatch = dispatch;
    }

    public OrderReceived getOrderReceived() {
        return orderReceived;
    }

    public void setOrderReceived(OrderReceived orderReceived) {
        this.orderReceived = orderReceived;
    }
}

