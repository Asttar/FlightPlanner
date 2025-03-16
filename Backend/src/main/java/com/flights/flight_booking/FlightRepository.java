package com.flights.flight_booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDestinationAndDateAndTimeAndPriceBetween(
            String destination, String date, String time, double priceMin, double priceMax
    );
    List<Flight> findByPriceBetween(double priceMin, double priceMax);
    List<Flight> findByDestinationAndPriceBetween(String destination, double priceMin, double priceMax);
    List<Flight> findByDateAndPriceBetween(String date, double priceMin, double priceMax);
    List<Flight> findByTimeAndPriceBetween(String time, double priceMin, double priceMax);
    List<Flight> findByDestinationAndDateAndPriceBetween(String destination, String date, double priceMin, double priceMax);
    List<Flight> findByDestinationAndTimeAndPriceBetween(String destination, String time, double priceMin, double priceMax);
    List<Flight> findByDateAndTimeAndPriceBetween(String date, String time, double priceMin, double priceMax);

}