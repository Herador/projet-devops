package com.example.rentalservice.services;

import com.example.rentalservice.entities.Rental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RentalServiceTest {

    private RentalService rentalService;

    @BeforeEach
    public void setUp() {
        rentalService = new RentalService();
    }

    @Test
    public void testAddRental() {
        Rental rental = new Rental("B001", "ABC123", "Alice", 3, 450.0);
        rentalService.addRental(rental);
        assertEquals(1, rentalService.getRentals().size());
    }

    @Test
    public void testGetRentals() {
        rentalService.addRental(new Rental("B001", "ABC123", "Alice", 3, 450.0));
        rentalService.addRental(new Rental("B002", "XYZ789", "Bob", 2, 360.0));
        assertEquals(2, rentalService.getRentals().size());
    }

    @Test
    public void testGetRentalById() {
        Rental rental = new Rental("B001", "ABC123", "Alice", 3, 450.0);
        rentalService.addRental(rental);
        Rental result = rentalService.getRental("B001");
        assertNotNull(result);
        assertEquals("Alice", result.getCustomerName());
    }

    @Test
    public void testGetRentalNotFound() {
        Rental result = rentalService.getRental("NOTFOUND");
        assertNull(result);
    }

    @Test
    public void testComputeTotalPrice() {
        double total = rentalService.computeTotalPrice(150.0, 3);
        assertEquals(450.0, total);
    }

    @Test
    public void testComputeTotalPriceZeroDays() {
        double total = rentalService.computeTotalPrice(150.0, 0);
        assertEquals(0.0, total);
    }
}
