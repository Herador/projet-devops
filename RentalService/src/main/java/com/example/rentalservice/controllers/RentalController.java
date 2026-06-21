package com.example.rentalservice.controllers;

import com.example.rentalservice.clients.CarClient;
import com.example.rentalservice.entities.Car;
import com.example.rentalservice.entities.Rental;
import com.example.rentalservice.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private CarClient carClient;

    @GetMapping("/")
    public String sayHello() {
        return "Rental service up";
    }

    @PostMapping("/rentals")
    public Rental addRental(@RequestBody Rental rental) {
        Car car = carClient.getCar(rental.getPlateNumber());
        double dailyPrice = (car != null) ? car.getPrice() : 0.0;
        rental.setTotalPrice(rentalService.computeTotalPrice(dailyPrice, rental.getDays()));
        rentalService.addRental(rental);
        return rental;
    }

    @GetMapping("/rentals")
    public List<Rental> getRentals() {
        return rentalService.getRentals();
    }

    @GetMapping("/rentals/{bookingId}")
    public Rental getRental(@PathVariable String bookingId) {
        return rentalService.getRental(bookingId);
    }
}
