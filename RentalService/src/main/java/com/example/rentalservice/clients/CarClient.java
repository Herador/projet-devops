package com.example.rentalservice.clients;

import com.example.rentalservice.entities.Car;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CarClient {

    private final RestClient restClient;

    public CarClient(@Value("${car.service.url:http://localhost:8080}") String baseUrl) {
        this.restClient = RestClient.create(baseUrl);
    }

    public Car getCar(String plateNumber) {
        try {
            return restClient.get()
                    .uri("/cars/{plateNumber}", plateNumber)
                    .retrieve()
                    .body(Car.class);
        } catch (Exception e) {
            return null;
        }
    }
}
