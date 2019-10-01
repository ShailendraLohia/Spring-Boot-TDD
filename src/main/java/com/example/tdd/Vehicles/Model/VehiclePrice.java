package com.example.tdd.Vehicles.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiclePrice {
    @NotEmpty(message = "model cannot be Empty")
    private String model;
    private String yearBuilt;
    private Double price;
}
