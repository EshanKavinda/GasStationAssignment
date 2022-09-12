package com.eshanperera.dispatchservice.controller;

import ch.qos.logback.core.boolex.EvaluationException;
import com.eshanperera.dispatchservice.model.Event;
import com.eshanperera.dispatchservice.service.kafka.Producer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DispatchController {

    @Autowired
    Producer producer;

    @RequestMapping(value = "/dispatch", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String disptch(@RequestBody Map<String, String> val) throws JsonProcessingException {
        String orderid = val.get("oid");
        producer.publishToTopic(new Event(orderid, "to-dispatch-process", null, "pending", "dispatch-service"));
        return "Dispatch request sent for order id ("+orderid+"). please check status in few seconds.";


    }

}
