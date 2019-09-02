package com.example.tdd.Vehicles.Service;

import com.example.tdd.Vehicles.Model.VehicleRequest;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    public VehicleRequest addVehicle(VehicleRequest vehicleRequest) {
        return vehicleRequest;
    }
}
