package com.eshanperera.allocationservice.service.kafka;

import com.eshanperera.allocationservice.models.Event;
import com.eshanperera.allocationservice.models.MainStock;
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

        String now = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());

        if(event.getType().equals("NEW_ORDER")){
            String allocationResult, responseMsg, allocatedDate=null;
            String eventData = event.getData();
            int fuelTypeId = Integer.parseInt(eventData.split("#")[0]);
            double requestQty = Double.parseDouble(eventData.split("#")[1]);
            double availableQty = mainStockService.getStockBalance(fuelTypeId);
            if (availableQty >= requestQty){
                Reservation r = reservationService.addOrUpdateReservation(new Reservation(event.getOrderId(), fuelTypeId, requestQty, now, 1));
                if (r.getId() != null){
                    allocationResult = "success";
                    responseMsg = "success";
                    allocatedDate = r.getAllocatedDate();
                } else{
                    allocationResult = "failed";
                    responseMsg = "Reservation Error";
                }
            }else {
                allocationResult = "failed";
                responseMsg = "No stock available.";
            }
            String resEventData = allocatedDate+"#"+responseMsg;
            producer.publishToTopic(new Event(event.getOrderId(), "allocation-complete", resEventData, allocationResult, "allocation-service"));

        }else if (event.getType().equals("TO_DISPATCH")){
            String dispatchResult, responseMsg, dispatchDate = null;
            String oid = event.getOrderId();
            Reservation reservation = reservationService.findReservation(oid);
            int fuelType = reservation.getFuelType();
            double reservedQuantity = reservation.getAmount(), updatedQty;

            MainStock mainStock = mainStockService.findStock(fuelType);
            double mainStockQty = mainStock.getAvailableQuantity();
            if (mainStockQty >= reservedQuantity){
                updatedQty = mainStockQty-reservedQuantity;
                mainStock.setAvailableQuantity(updatedQty);
                reservation.setStatus(0);
                mainStockService.addOrUpdateStock(mainStock);
                reservationService.addOrUpdateReservation(reservation);
                dispatchResult = "success";
                responseMsg = "success";
                dispatchDate = now;
            }else {
                dispatchResult = "failed";
                responseMsg = "stock mismatch";
            }
            String resEventData = dispatchDate+"#"+responseMsg;
            producer.publishToTopic(new Event(event.getOrderId(), "dispatch-complete", resEventData, dispatchResult, "allocation-service"));

        }else {
            System.out.println("Message not related to allocation based... Process ignored...");
        }
    }
}
