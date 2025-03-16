package com.flights.flight_booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public void run(String... args) throws Exception {
        if (flightRepository.count() != 0) {
            return;
        }
        List<Flight> flights = Arrays.asList(
                // Flights to New York
                new Flight("New York", "2025-04-10", "14:30", 450.0),
                new Flight("New York", "2025-04-11", "09:00", 420.0),
                new Flight("New York", "2025-04-12", "16:45", 460.0),
                new Flight("New York", "2025-04-15", "21:30", 490.0),
                new Flight("New York", "2025-04-20", "08:15", 410.0),

                // Flights to London
                new Flight("London", "2025-05-12", "09:15", 300.0),
                new Flight("London", "2025-05-13", "11:30", 320.0),
                new Flight("London", "2025-05-14", "14:50", 290.0),
                new Flight("London", "2025-05-18", "18:10", 350.0),
                new Flight("London", "2025-05-22", "07:40", 310.0),

                // Flights to Paris
                new Flight("Paris", "2025-06-25", "16:45", 280.0),
                new Flight("Paris", "2025-06-26", "08:00", 260.0),
                new Flight("Paris", "2025-06-28", "13:25", 300.0),
                new Flight("Paris", "2025-07-01", "19:10", 320.0),
                new Flight("Paris", "2025-07-05", "10:45", 270.0),

                // Flights to Tokyo
                new Flight("Tokyo", "2025-07-10", "10:00", 500.0),
                new Flight("Tokyo", "2025-07-11", "14:30", 520.0),
                new Flight("Tokyo", "2025-07-15", "22:15", 540.0),
                new Flight("Tokyo", "2025-07-20", "06:45", 510.0),
                new Flight("Tokyo", "2025-07-25", "12:00", 530.0),

                // Flights to Dubai
                new Flight("Dubai", "2025-08-10", "23:00", 550.0),
                new Flight("Dubai", "2025-08-11", "08:45", 530.0),
                new Flight("Dubai", "2025-08-14", "15:30", 560.0),
                new Flight("Dubai", "2025-08-18", "20:10", 570.0),
                new Flight("Dubai", "2025-08-22", "11:25", 540.0),

                // Flights to Los Angeles
                new Flight("Los Angeles", "2025-09-05", "08:00", 420.0),
                new Flight("Los Angeles", "2025-09-06", "10:20", 400.0),
                new Flight("Los Angeles", "2025-09-10", "13:50", 430.0),
                new Flight("Los Angeles", "2025-09-15", "19:30", 460.0),
                new Flight("Los Angeles", "2025-09-20", "07:10", 410.0),

                // Flights to Sydney
                new Flight("Sydney", "2025-10-10", "22:30", 600.0),
                new Flight("Sydney", "2025-10-12", "06:15", 620.0),
                new Flight("Sydney", "2025-10-15", "14:00", 590.0),
                new Flight("Sydney", "2025-10-18", "20:40", 630.0),
                new Flight("Sydney", "2025-10-22", "09:30", 610.0)
        );

        flightRepository.saveAll(flights);
    }
}
