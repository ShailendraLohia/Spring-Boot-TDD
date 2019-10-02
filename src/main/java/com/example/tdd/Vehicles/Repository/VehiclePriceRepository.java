package com.example.tdd.Vehicles.Repository;

import com.example.tdd.Vehicles.Model.VehiclePrice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiclePriceRepository extends CrudRepository<VehiclePrice,String> {

}
