package com.example.tdd.Vehicles.Service;

import com.example.tdd.Vehicles.Model.VehiclePriceDetails;
import com.example.tdd.Vehicles.Repository.VehiclePriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehiclePriceService {
    private VehiclePriceRepository vehiclePriceRepository;
    public VehiclePriceService(VehiclePriceRepository vehiclePriceRepository) {
        this.vehiclePriceRepository=vehiclePriceRepository;
    }
    public String addVehiclePriceData(VehiclePriceDetails vehiclePriceDetails) {
        vehiclePriceRepository.save(vehiclePriceDetails.getVehiclePriceDetails());
        return "VehiclePriceAdded";
    }
}
