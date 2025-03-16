package com.flights.flight_booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }


    public List<Flight> findByDestinationAndDateAndTimeAndPriceBetween(String destination, String date, String time, double price_min, double price_max) {
        return flightRepository.findByDestinationAndDateAndTimeAndPriceBetween(destination, date, time, price_min, price_max);
    }

    public List<Flight> findByPriceBetween(double price_min, double price_max) {
        return flightRepository.findByPriceBetween(price_min, price_max);
    }

    public List<Flight> findByDestinationAndPriceBetween(String destination, double price_min, double price_max) {
        return flightRepository.findByDestinationAndPriceBetween(destination, price_min, price_max);
    }

    public List<Flight> findByDateAndPriceBetween(String date, double price_min, double price_max) {
        return flightRepository.findByDateAndPriceBetween(date, price_min, price_max);
    }

    public List<Flight> findByTimeAndPriceBetween(String time, double price_min, double price_max) {
        return flightRepository.findByTimeAndPriceBetween(time, price_min, price_max);
    }

    public List<Flight> findByDestinationAndDateAndPriceBetween(String destination, String date, double price_min, double price_max) {
        return flightRepository.findByDestinationAndDateAndPriceBetween(destination, date, price_min, price_max);
    }


    public List<Flight> findByDestinationAndTimeAndPriceBetween(String destination, String time, double price_min, double price_max) {
        return flightRepository.findByDestinationAndTimeAndPriceBetween(destination, time, price_min, price_max);
    }

    public List<Flight> findByDateAndTimeAndPriceBetween(String date, String time, double price_min, double price_max) {
        return flightRepository.findByDateAndTimeAndPriceBetween(date, time, price_min, price_max);
    }



}
