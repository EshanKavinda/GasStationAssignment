package com.eshanperera.allocationservice.service;

import com.eshanperera.allocationservice.models.MainStock;
import com.eshanperera.allocationservice.repository.MainStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MainStockServiceImpl implements MainStockService{

    @Autowired
    MainStockRepository stockRepository;

    @Override
    public MainStock addOrUpdateStock(MainStock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public double getStockBalance(int fuelTypeId) {
        Optional<MainStock> stock = stockRepository.findById(fuelTypeId);
        if (stock.isPresent()){
            return stock.get().getAvailableQuantity();
        }else {
            return 0;
        }
    }
}
