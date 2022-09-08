package com.eshanperera.allocationservice.service;

import com.eshanperera.allocationservice.models.Reservation;

public interface FuelReservationService {
    Reservation addOrUpdateReservation(Reservation reservation);

    Reservation findReservation(String oid);

}
