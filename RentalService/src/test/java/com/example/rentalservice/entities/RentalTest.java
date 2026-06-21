package com.example.rentalservice.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RentalTest {

    @Test
    public void testRentalConstructor() {
        Rental rental = new Rental("B001", "ABC123", "Alice", 3, 450.0);
        assertEquals("B001", rental.getBookingId());
        assertEquals("ABC123", rental.getPlateNumber());
        assertEquals("Alice", rental.getCustomerName());
        assertEquals(3, rental.getDays());
        assertEquals(450.0, rental.getTotalPrice());
    }

    @Test
    public void testSetBookingId() {
        Rental rental = new Rental();
        rental.setBookingId("B002");
        assertEquals("B002", rental.getBookingId());
    }

    @Test
    public void testSetCustomerName() {
        Rental rental = new Rental();
        rental.setCustomerName("Bob");
        assertEquals("Bob", rental.getCustomerName());
    }

    @Test
    public void testSetDays() {
        Rental rental = new Rental();
        rental.setDays(5);
        assertEquals(5, rental.getDays());
    }

    @Test
    public void testSetTotalPrice() {
        Rental rental = new Rental();
        rental.setTotalPrice(750.0);
        assertEquals(750.0, rental.getTotalPrice());
    }

    @Test
    public void testToString() {
        Rental rental = new Rental("B001", "ABC123", "Alice", 3, 450.0);
        String expected = "Rental{bookingId='B001', plateNumber='ABC123', customerName='Alice', days=3, totalPrice=450.0}";
        assertEquals(expected, rental.toString());
    }
}
