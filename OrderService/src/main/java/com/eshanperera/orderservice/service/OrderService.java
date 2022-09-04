package com.eshanperera.orderservice.service;

import com.eshanperera.orderservice.models.Order;

public interface OrderService {

    Order saveNewOrder(Order order);
    Order updateOrder(Order order);
    Order findOrder(String oid);

}
