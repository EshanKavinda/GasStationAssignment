package com.eshanperera.orderservice.service.kafka;

import com.eshanperera.orderservice.models.Event;
import com.eshanperera.orderservice.models.Order;
import com.eshanperera.orderservice.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @Autowired
    Producer producer;
    @Autowired
    OrderService orderService;

    ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "new-order-response")
    public void readFromTopic(String message) throws JsonProcessingException {

        System.out.println("Incomming message found. : " + message);
        Event event = objectMapper.readValue(message, Event.class);

        if (event.getType().equals("allocation-complete")){
            String eventData = event.getData();
            String allocatedDate = eventData.split("#")[0];
            String responseMsg = eventData.split("#")[1];
            Order order = orderService.findOrder(event.getOrderId());
            if (event.getResult().equals("success")){
                order.setCurrentStatus("ALLOCATED");
                order.getWorkFlowStatus().getAllocation().setStatus("success");
                order.getWorkFlowStatus().getAllocation().setDate(allocatedDate);
            }else {
                order.getWorkFlowStatus().getAllocation().setStatus("failed: "+responseMsg);
            }
            orderService.updateOrder(order);
        }

    }


    


}
