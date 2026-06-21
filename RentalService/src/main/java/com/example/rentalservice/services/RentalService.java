package com.example.rentalservice.services;

import com.example.rentalservice.entities.Rental;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentalService {

    private List<Rental> rentals = new ArrayList<>();

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public Rental getRental(String bookingId) {
        return rentals.stream()
                .filter(rental -> rental.getBookingId().equals(bookingId))
                .findFirst()
                .orElse(null);
    }

    public List<Rental> getRentals() {
        return new ArrayList<>(rentals);
    }

    public double computeTotalPrice(double dailyPrice, int days) {
        return dailyPrice * days;
    }
}
