package com.eshanperera.orderservice.controller;

import com.eshanperera.orderservice.models.Order;
import com.eshanperera.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public Order newOrder(@RequestBody Order order){

        return orderService.saveNewOrder(order);
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public Order getOrder(@RequestParam String oid){
        return orderService.findOrder(oid);
    }

    @RequestMapping(value = "/orderstobedispatch", method = RequestMethod.GET)
    public List<Order> getOrdersToBeDispatched(){
        return orderService.ordersToBeDispatched();
    }

}
