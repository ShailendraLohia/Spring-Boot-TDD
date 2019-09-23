package com.example.tdd.Vehicles.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle_data")
public class VehicleData {


    @NotEmpty(message="data cannot be Empty")
    @Id
    @Column(name = "vin_number")
    private String VIN;
    private String model;
    private String yearBuilt;
    private Double price;
}
