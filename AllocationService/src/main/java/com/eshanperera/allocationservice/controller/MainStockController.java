package com.eshanperera.allocationservice.controller;

import com.eshanperera.allocationservice.models.MainStock;
import com.eshanperera.allocationservice.service.MainStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainStockController {

    @Autowired
    MainStockService mainStockService;

    @RequestMapping(value = "/updateStock", method = RequestMethod.POST)
    public MainStock addUpdateStock(){
        return mainStockService.addOrUpdateStock(new MainStock(1, "Petrol", 100000.00, 1));
    }




}
