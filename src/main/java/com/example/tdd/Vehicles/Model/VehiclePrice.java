package com.example.tdd.Vehicles.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="vehicle_price")
public class VehiclePrice {
    @NotEmpty(message = "model cannot be Empty")
    @Id
    private String model;
    private String yearBuilt;
    private Double price;
}
