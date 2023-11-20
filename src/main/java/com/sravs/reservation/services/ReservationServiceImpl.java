package com.sravs.reservation.services;

import com.sravs.reservation.dto.ReservationRequest;
import com.sravs.reservation.entities.Flight;
import com.sravs.reservation.entities.Passenger;
import com.sravs.reservation.entities.Reservation;
import com.sravs.reservation.repos.FlightRepository;
import com.sravs.reservation.repos.PassengerRepository;
import com.sravs.reservation.repos.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService{
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    PassengerRepository passengerRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Override
    public Reservation bookFlight(ReservationRequest request) {

        Long flightId = request.getFlightId();


        Flight flight = flightRepository.findById(flightId).orElse(null);

        Passenger passenger = new Passenger();
        passenger.setFirstName(request.getPassengerFirstName());
        passenger.setLastName(request.getPassengerLastName());
        passenger.setEmail((request.getPassengerEmail()));
        passenger.setPhone(request.getPassengerPhone());
        Passenger savedPassenger = passengerRepository.save(passenger);

        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(savedPassenger);
        reservation.setCheckedIn(false);
        Reservation savedReservation = reservationRepository.save(reservation);
        return savedReservation;
    }
}
