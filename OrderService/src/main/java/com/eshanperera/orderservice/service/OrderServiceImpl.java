package com.eshanperera.orderservice.service;

import com.eshanperera.orderservice.models.Event;
import com.eshanperera.orderservice.models.Order;
import com.eshanperera.orderservice.models.workflow.*;
import com.eshanperera.orderservice.repository.OrderRepository;
import com.eshanperera.orderservice.service.kafka.Producer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    Producer producer;

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

        Order result = orderRepository.save(order);

        Event event = new Event(
                result.getOrderId(),
                "NEW_ORDER",
                result.getFuelTypeId()+"#"+result.getQuantity(),
                "PENDING",
                "order-service"
                );

        try {
            producer.publishToTopic(event);
        } catch (JsonProcessingException e) {
            System.out.println("Error while publishing event from order service");
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findOrder(String oid) {
        Optional<Order> order = orderRepository.findById(oid);
        if (order.isPresent()){
            return order.get();
        }else {
            return null;
        }
    }
}
