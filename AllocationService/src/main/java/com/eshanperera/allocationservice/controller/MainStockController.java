package com.eshanperera.allocationservice.controller;

import com.eshanperera.allocationservice.models.MainStock;
import com.eshanperera.allocationservice.service.MainStockService;
import com.sun.tools.javac.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainStockController {

    @Autowired
    MainStockService mainStockService;

    @RequestMapping(value = "/cpcmainstock", method = RequestMethod.POST)
    public MainStock addUpdateStock(@RequestBody MainStock stock){
        return mainStockService.addOrUpdateStock(stock);
    }
}
