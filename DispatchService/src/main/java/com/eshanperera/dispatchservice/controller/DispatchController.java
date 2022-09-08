package com.eshanperera.dispatchservice.controller;

import com.eshanperera.dispatchservice.model.Event;
import com.eshanperera.dispatchservice.service.kafka.Producer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DispatchController {

    @Autowired
    Producer producer;

    @RequestMapping(value = "/dispatch", method = RequestMethod.POST)
    public String disptch(@RequestParam String oid) throws JsonProcessingException {
        producer.publishToTopic(new Event(oid, "to-dispatch-process", null, "pending", "dispatch-service"));
        return "request sent... please check status in few seconds....";
    }

}
