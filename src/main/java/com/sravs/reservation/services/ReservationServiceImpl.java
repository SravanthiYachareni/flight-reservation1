package com.sravs.reservation.services;

import com.sravs.reservation.controller.FlightController;
import com.sravs.reservation.dto.ReservationRequest;
import com.sravs.reservation.entities.Flight;
import com.sravs.reservation.entities.Passenger;
import com.sravs.reservation.entities.Reservation;
import com.sravs.reservation.repos.FlightRepository;
import com.sravs.reservation.repos.PassengerRepository;
import com.sravs.reservation.repos.ReservationRepository;
import com.sravs.reservation.util.EmailUtil;
import com.sravs.reservation.util.PDFGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    PassengerRepository passengerRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    PDFGenerator generator;
    @Autowired
    EmailUtil emailUtil;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Override
    public Reservation bookFlight(ReservationRequest request) {
        LOGGER.info("inside bookFlight");

        Long flightId = request.getFlightId();
        LOGGER.info("Fetching Flight for the flight id " + flightId);


        Flight flight = flightRepository.findById(flightId).orElse(null);

        Passenger passenger = new Passenger();
        passenger.setFirstName(request.getPassengerFirstName());
        passenger.setLastName(request.getPassengerLastName());
        passenger.setEmail((request.getPassengerEmail()));
        passenger.setPhone(request.getPassengerPhone());
        LOGGER.info("Saving the passenger" + passenger);
        Passenger savedPassenger = passengerRepository.save(passenger);

        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(savedPassenger);
        reservation.setCheckedIn(false);
        LOGGER.info("Saving the reservation" + reservation);
        Reservation savedReservation = reservationRepository.save(reservation);
        String filePath = "C:\\Users\\srava\\OneDrive\\Documents\\reservations\\reservation" + savedReservation.getId() + ".pdf";
        LOGGER.info("Generating the itinerary");
        generator.generateItinerary(savedReservation, filePath);
        LOGGER.info("Emailing the itinerary");
        emailUtil.sendItinerary(passenger.getEmail(), filePath);
        return savedReservation;
    }
}
