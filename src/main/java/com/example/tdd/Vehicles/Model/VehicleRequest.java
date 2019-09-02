package com.example.tdd.Vehicles.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequest {


    @NotNull(message = "cannot be null")
//    @NotEmpty(message= "cannot be empty")
    private VehicleData vehicles;
//
//    public VehicleRequest() {
//    }
//
//    public VehicleRequest(VehicleData vehicles) {
//        this.vehicles = vehicles;
//    }
//
//    public VehicleData getVehicles() {
//        return vehicles;
//    }
//
//    public void setVehicles(VehicleData vehicles) {
//        this.vehicles = vehicles;
//    }
}
