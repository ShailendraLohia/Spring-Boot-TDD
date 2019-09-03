package com.example.tdd.Vehicles.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleData {

    @NotEmpty(message="data cannot be Empty")
    //@NotNull(message="data cannot be null")
    private String VIN;
    private String model;
    private String yearBuilt;
    private Double price;
}
