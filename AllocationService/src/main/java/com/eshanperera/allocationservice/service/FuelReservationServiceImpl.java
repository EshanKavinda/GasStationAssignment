package com.eshanperera.allocationservice.service;

import com.eshanperera.allocationservice.models.Reservation;
import com.eshanperera.allocationservice.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FuelReservationServiceImpl implements FuelReservationService{

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
}
