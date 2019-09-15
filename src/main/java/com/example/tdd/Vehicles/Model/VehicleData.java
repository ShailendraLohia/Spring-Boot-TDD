package com.example.tdd.Vehicles.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VehicleData {


    @NotEmpty(message="data cannot be Empty")
    @Id
    private String VIN;
    private String model;
    private String yearBuilt;
    private Double price;
}
