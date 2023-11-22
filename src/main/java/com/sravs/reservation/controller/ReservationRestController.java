package com.sravs.reservation.controller;

import com.sravs.reservation.dto.ReservationUpdateRequest;
import com.sravs.reservation.entities.Reservation;
import com.sravs.reservation.repos.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ReservationRestController {
    @Autowired
    ReservationRepository reservationRepository;

    @RequestMapping("/reservations/{id}")
    public Reservation findReservation(@PathVariable("id") Long id) {
        Reservation reservation = reservationRepository.findById(id).get();
        return reservation;

    }

    @RequestMapping("/reservations")
    public Reservation updateReservation(@RequestBody  ReservationUpdateRequest request) {
        Reservation reservation = reservationRepository.findById(request.getId()).get();
        reservation.setNumberOfBags(request.getNumberOfBags());
        reservation.setCheckedIn(request.getCheckedIn());
        Reservation updatedReservation = reservationRepository.save(reservation);
        return updatedReservation;
    }
}
