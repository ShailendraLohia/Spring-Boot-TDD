package com.example.tdd.Vehicles.Controller;

import com.example.tdd.Vehicles.Model.VehiclePriceDetails;
import com.example.tdd.Vehicles.Service.VehiclePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/price")
@Validated
public class VehiclePriceController {
    @Autowired
    private VehiclePriceService vehiclePriceService;

    @PostMapping
    public ResponseEntity<String> addVehiclePrice(@Valid @RequestBody VehiclePriceDetails vehiclePriceDetails) {
        return new ResponseEntity<>(vehiclePriceService.addVehiclePriceData(
                                vehiclePriceDetails),HttpStatus.CREATED);
    }
}
