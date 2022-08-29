package com.eshanperera.allocationservice.repository;

import com.eshanperera.allocationservice.models.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservationRepository extends MongoRepository<Reservation, String> {
}
