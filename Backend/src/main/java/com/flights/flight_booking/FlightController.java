package com.flights.flight_booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights/")
@CrossOrigin(origins = "http://localhost:8080")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("")
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("{id}")
    public ResponseEntity<Flight> getSpecificFlight(@PathVariable Long id) {
        return flightService.getFlightById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("destination/{destination}/date/{date}/time/{time}/price_min/{price_min}/price_max/{price_max}")
    public List<Flight> findByDestinationAndDateAndTimeAndPriceBetween(@PathVariable String destination, @PathVariable String date, @PathVariable String time, @PathVariable double price_min, @PathVariable double price_max) {
        return flightService.findByDestinationAndDateAndTimeAndPriceBetween(destination, date, time, price_min, price_max);
    }

    @GetMapping("/price_min/{price_min}/price_max/{price_max}")
    public List<Flight> findByPriceBetween(@PathVariable double price_min, @PathVariable double price_max) {
        return flightService.findByPriceBetween(price_min, price_max);
    }

    @GetMapping("destination/{destination}/price_min/{price_min}/price_max/{price_max}")
    public List<Flight> findByDestinationAndPriceBetween(@PathVariable String destination, @PathVariable double price_min, @PathVariable double price_max) {
        return flightService.findByDestinationAndPriceBetween(destination, price_min, price_max);
    }

    @GetMapping("date/{date}/price_min/{price_min}/price_max/{price_max}")
    public List<Flight> findByDateAndPriceBetween(@PathVariable String date, @PathVariable double price_min, @PathVariable double price_max) {
        return flightService.findByDateAndPriceBetween(date, price_min, price_max);
    }

    @GetMapping("time/{time}/price_min/{price_min}/price_max/{price_max}")
    public List<Flight> findByTimeAndPriceBetween(@PathVariable String time, @PathVariable double price_min, @PathVariable double price_max) {
        return flightService.findByTimeAndPriceBetween(time, price_min, price_max);
    }


    @GetMapping("destination/{destination}/date/{date}/price_min/{price_min}/price_max/{price_max}")
    public List<Flight> findByDestinationAndDateAndPriceBetween(@PathVariable String destination, @PathVariable String date, @PathVariable double price_min, @PathVariable double price_max) {
        return flightService.findByDestinationAndDateAndPriceBetween(destination, date, price_min, price_max);
    }

    @GetMapping("destination/{destination}/time/{time}/price_min/{price_min}/price_max/{price_max}")
    public List<Flight> findByDestinationAndTimeAndPriceBetween(@PathVariable String destination, @PathVariable String time, @PathVariable double price_min, @PathVariable double price_max) {
        return flightService.findByDestinationAndTimeAndPriceBetween(destination, time, price_min, price_max);
    }

    @GetMapping("date/{date}/time/{time}/price_min/{price_min}/price_max/{price_max}")
    public List<Flight> findByDateAndTimeAndPriceBetween(@PathVariable String date, @PathVariable String time, @PathVariable double price_min, @PathVariable double price_max) {
        return flightService.findByDateAndTimeAndPriceBetween(date, time, price_min, price_max);
    }





}
