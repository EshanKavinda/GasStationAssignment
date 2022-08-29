package com.eshanperera.allocationservice.service.kafka;

import com.eshanperera.allocationservice.models.Event;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @Autowired
    Producer producer;

    ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "new-order")
    public void readFromTopic(String message) throws InterruptedException, JsonProcessingException {
        System.out.println("Incomming message found. : " + message);
        Event event = objectMapper.readValue(message, Event.class);




    }
    


}
