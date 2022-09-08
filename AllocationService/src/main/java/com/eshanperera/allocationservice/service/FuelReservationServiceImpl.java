package com.eshanperera.allocationservice.service;

import com.eshanperera.allocationservice.models.Reservation;
import com.eshanperera.allocationservice.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FuelReservationServiceImpl implements FuelReservationService{

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Reservation addOrUpdateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation findReservation(String oid) {
        Reservation reserveOrder = null;
        List<Reservation> reservationList = reservationRepository.findAll();
        for(Reservation reservation: reservationList){
            if (reservation.getOrderId().equals(oid)){
                reserveOrder = reservation;
            }
        }
        return reserveOrder;
    }
}
