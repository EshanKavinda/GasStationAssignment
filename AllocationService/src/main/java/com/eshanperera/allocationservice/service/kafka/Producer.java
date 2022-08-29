package com.eshanperera.allocationservice.service.kafka;

import com.eshanperera.allocationservice.models.Event;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    public static final String TOPIC = "new-order-response";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    ObjectMapper objectMapper = new ObjectMapper();

    public void publishToTopic(Event event) throws JsonProcessingException {
        System.out.println("publishing event to " + this.TOPIC + " topic from allocation service");

        String eventStr = objectMapper.writeValueAsString(event);
        this.kafkaTemplate.send(this.TOPIC, eventStr);
    }




}
