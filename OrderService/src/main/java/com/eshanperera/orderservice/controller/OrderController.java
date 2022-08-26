package com.eshanperera.orderservice.controller;

import com.eshanperera.orderservice.models.Order;
import com.eshanperera.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public Order newOrder(@RequestBody Order order){
        return orderService.saveNewOrder(order);
    }

}
