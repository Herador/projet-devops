package com.example.rentalservice.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    @Test
    public void testCarConstructor() {
        Car car = new Car("ABC123", "Toyota", 150.0);
        assertEquals("ABC123", car.getPlateNumber());
        assertEquals("Toyota", car.getBrand());
        assertEquals(150.0, car.getPrice());
    }

    @Test
    public void testNoArgConstructorAndSetters() {
        Car car = new Car();
        car.setPlateNumber("XYZ789");
        car.setBrand("Honda");
        car.setPrice(180.0);
        assertEquals("XYZ789", car.getPlateNumber());
        assertEquals("Honda", car.getBrand());
        assertEquals(180.0, car.getPrice());
    }
}
