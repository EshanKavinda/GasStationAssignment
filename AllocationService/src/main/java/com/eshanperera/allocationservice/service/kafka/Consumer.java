package com.eshanperera.allocationservice.service.kafka;

import com.eshanperera.allocationservice.models.Event;
import com.eshanperera.allocationservice.models.Reservation;
import com.eshanperera.allocationservice.service.FuelReservationService;
import com.eshanperera.allocationservice.service.MainStockService;
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
    MainStockService mainStockService;
    @Autowired
    FuelReservationService reservationService;

    ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "new-order")
    public void readFromTopic(String message) throws InterruptedException, JsonProcessingException {
        System.out.println("Incomming message found. : " + message);
        Event event = objectMapper.readValue(message, Event.class);

        String eventData = event.getData();
        int fuelTypeId = Integer.parseInt(eventData.split("#")[0]);
        double requestQty = Double.parseDouble(eventData.split("#")[1]);
        double availableQty = mainStockService.getStockBalance(fuelTypeId);

        String allocationResult = null;
        String responseMsg = null;
        String now = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());

        if(event.getType().equals("NEW_ORDER")){
            if (availableQty >= requestQty){
                Reservation r = reservationService.addReservation(new Reservation(event.getOrderId(), fuelTypeId, requestQty, now, 1));
                if (r.getId() != null){
                    allocationResult = "success";
                } else{
                    allocationResult = "failed";
                    responseMsg = "Reservation Error";
                }
            }else {
                allocationResult = "failed";
                responseMsg = "No stock available.";
            }
//            producer.publishToTopic(new Event(event.getOrderId(), "allocation-complete", ));
        }else {
            System.out.println("Event is not related to allocation. process ignored");
        }
        


    }
    


}
