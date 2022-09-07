package com.eshanperera.orderservice.service.kafka;

import com.eshanperera.orderservice.models.Event;
import com.eshanperera.orderservice.models.Order;
import com.eshanperera.orderservice.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        String now = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
        Event event = objectMapper.readValue(message, Event.class);

        if (event.getType().equals("allocation-complete")){
            String eventData = event.getData();
            String allocatedDateTime = eventData.split("#")[0];
            String responseMsg = eventData.split("#")[1];
            Order order = orderService.findOrder(event.getOrderId());
            if (event.getResult().equals("success")){
                order.setCurrentStatus("ALLOCATED");
                order.getWorkFlowStatus().getAllocation().setStatus("success");
                order.getWorkFlowStatus().getAllocation().setDate(allocatedDateTime);
            }else {
                order.getWorkFlowStatus().getAllocation().setStatus("failed: "+responseMsg);
            }
            Order o1 = orderService.updateOrder(order);
            if (o1.getCurrentStatus().equals("ALLOCATED")){
                String allocatedDate = o1.getWorkFlowStatus().getAllocation().getDate().split(" ")[0];
                producer.publishToTopic(new Event(o1.getOrderId(), "ALLOCATION_COMPLETE", allocatedDate, "pending", "order-service"));
            }else {
                System.out.println("Allocation status is not updated....");
            }
        } else if (event.getType().equals("schedule-complete")) {
            String scheduledDate = event.getData();
            Order order = orderService.findOrder(event.getOrderId());
            if (event.getResult().equals("success")){
                order.setCurrentStatus("SCHEDULLED");
                order.getWorkFlowStatus().getSchedule().setStatus("success");
                order.getWorkFlowStatus().getSchedule().setArrival_date(scheduledDate);
            }else {
                order.getWorkFlowStatus().getSchedule().setStatus("failed");
            }
            order.getWorkFlowStatus().getSchedule().setProcessdate(now);
            orderService.updateOrder(order);
        }

    }


    


}
