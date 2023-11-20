package com.sravs.reservation.services;

import com.sravs.reservation.dto.ReservationRequest;
import com.sravs.reservation.entities.Reservation;

public interface ReservationService {
    public Reservation bookFlight(ReservationRequest request) ;
}
