package com.example.tdd.Vehicles.Repository;

import com.example.tdd.Vehicles.Model.VehicleData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends CrudRepository<VehicleData,String> {
//    public VehicleRequest save(VehicleRequest vehicleRequest) {
//        return vehicleRequest;
//    }
//
//    public Optional<VehicleRequest> findById(String anyString) {
//        VehicleRequest vehicleRequest = new VehicleRequest();
//        return Optional.of(vehicleRequest);
//    }
}
