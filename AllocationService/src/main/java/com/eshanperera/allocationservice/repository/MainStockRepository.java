package com.eshanperera.allocationservice.repository;

import com.eshanperera.allocationservice.models.MainStock;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MainStockRepository extends MongoRepository<MainStock, Integer> {
}
