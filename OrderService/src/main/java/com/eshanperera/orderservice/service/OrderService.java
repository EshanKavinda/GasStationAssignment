package com.eshanperera.orderservice.service;

import com.eshanperera.orderservice.models.Order;

import java.util.List;

public interface OrderService {

    Order saveNewOrder(Order order);
    Order updateOrder(Order order);
    Order findOrder(String oid);

    List<Order> ordersToBeDispatched();

}
