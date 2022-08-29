package com.eshanperera.allocationservice.service;

import com.eshanperera.allocationservice.models.MainStock;

public interface MainStockService {

    MainStock addOrUpdateStock(MainStock stock);
    double getStockBalance(int fuelTypeId);

}
