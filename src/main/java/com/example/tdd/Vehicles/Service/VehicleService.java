package com.example.tdd.Vehicles.Service;

import com.example.tdd.Vehicles.Exception.VehicleNotFoundException;
import com.example.tdd.Vehicles.Model.VehicleData;
import com.example.tdd.Vehicles.Model.VehicleRequest;
import com.example.tdd.Vehicles.Repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleService {

//    @Autowired
    public VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public VehicleRequest addVehicle(VehicleRequest vehicleRequest) {
         vehicleRepository.save(vehicleRequest.getVehicles());
         return vehicleRequest;
    }

    public VehicleRequest getVehicleData(String vehicleId) {
        //vehicleRepository.
        Optional<VehicleData> vehicleData = vehicleRepository.findById(vehicleId);

//        if(null==vehicleRequest.get())
        if(!vehicleData.isPresent())
            throw new VehicleNotFoundException("Vehicle Not Found");

        //vehicleData.empty()

        VehicleRequest vehicleRequest=new VehicleRequest(vehicleData.get());
        return vehicleRequest;
    }

    public VehicleRequest updateVehicleData(VehicleRequest vehicleRequest,String vehicleId) {
        return vehicleRequest;
    }
}
