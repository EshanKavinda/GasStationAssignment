package com.eshanperera.allocationservice;

import com.eshanperera.allocationservice.models.MainStock;
import com.eshanperera.allocationservice.service.MainStockService;
import com.eshanperera.allocationservice.service.MainStockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
public class AllocationServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(AllocationServiceApplication.class, args);

    }

}
