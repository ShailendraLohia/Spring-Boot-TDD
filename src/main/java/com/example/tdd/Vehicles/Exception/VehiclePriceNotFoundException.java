package com.example.tdd.Vehicles.Exception;

public class VehiclePriceNotFoundException extends RuntimeException {
    public VehiclePriceNotFoundException(String message) {
        super(message);
    }
}
