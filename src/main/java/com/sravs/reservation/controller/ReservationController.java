package com.sravs.reservation.controller;

import com.sravs.reservation.dto.ReservationRequest;
import com.sravs.reservation.entities.Flight;
import com.sravs.reservation.entities.Reservation;
import com.sravs.reservation.repos.FlightRepository;
import com.sravs.reservation.services.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {
    @Autowired
    FlightRepository repository;
    @Autowired
    ReservationService reservationService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
    @RequestMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap model) {
        LOGGER.info(" showCompleteReservation() invoked with the flightId "+flightId);
        Flight flight = repository.findById(flightId).get();
        model.addAttribute("flight", flight);
        LOGGER.info("Flight is "+flight);
        return "/completeReservation";
    }

    @RequestMapping(value = "/completeReservation", method = RequestMethod.POST)
    public String completeReservation(ReservationRequest reservationRequest, ModelMap model) {
        Reservation reservation = reservationService.bookFlight(reservationRequest);
        LOGGER.info(" completeReservation"+reservationRequest);
        model.addAttribute("msg", "Reservation has created successfully and the id is " + reservation.getId());

        return "reservationConfirmation";
    }
}