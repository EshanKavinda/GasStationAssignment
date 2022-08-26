package com.eshanperera.orderservice.service;

import com.eshanperera.orderservice.models.Order;
import com.eshanperera.orderservice.models.workflow.*;
import com.eshanperera.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order saveNewOrder(Order order) {

        String now = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());

        order.setCurrentStatus("ORDER_CREATED");
        order.setWorkFlowStatus(new WorkFlowStatus(
                new Created("success", now),
                new Allocation("pending", "-"),
                new Schedule("pending", "-", "-"),
                new Dispatch("pending", "-"),
                new OrderReceived("pending", "-")
        ));

        return orderRepository.save(order);
    }
}
