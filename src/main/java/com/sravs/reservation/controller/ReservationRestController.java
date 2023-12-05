package com.sravs.reservation.controller;

import com.sravs.reservation.dto.ReservationUpdateRequest;
import com.sravs.reservation.entities.Reservation;
import com.sravs.reservation.repos.ReservationRepository;
import com.sravs.reservation.services.ReservationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ReservationRestController {
    @Autowired
    ReservationRepository reservationRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);

    @RequestMapping("/reservations/{id}")
    public Reservation findReservation(@PathVariable("id") Long id) {
        LOGGER.info("Inside findReservation() for id "+id);
        Reservation reservation = reservationRepository.findById(id).get();
        return reservation;

    }

    @RequestMapping("/reservations")
    public Reservation updateReservation(@RequestBody  ReservationUpdateRequest request) {
        LOGGER.info("updateReservation for "+request);
        Reservation reservation = reservationRepository.findById(request.getId()).get();
        reservation.setNumberOfBags(request.getNumberOfBags());
        reservation.setCheckedIn(request.getCheckedIn());
        LOGGER.info("Saving reservation");
        Reservation updatedReservation = reservationRepository.save(reservation);
        return updatedReservation;
    }
}
