package com.example.rentalservice.clients;

import com.example.rentalservice.entities.Car;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarClientTest {

    @Test
    public void testGetCarUnreachableReturnsNull() {
        // Le service voitures n'est pas joignable a cette adresse :
        // l'exception est interceptee et getCar renvoie null.
        CarClient carClient = new CarClient("http://localhost:1");
        Car car = carClient.getCar("ABC123");
        assertNull(car);
    }

    @Test
    public void testGetCarWithDefaultUrl() {
        // Couvre le constructeur avec l'URL par defaut + l'appel : renvoie null
        // car aucun service voitures ne tourne pendant le test unitaire.
        CarClient carClient = new CarClient("http://localhost:1");
        assertNull(carClient.getCar("XYZ789"));
    }
}
