package com.example.tdd.Vehicles.Controller;

import com.example.tdd.Vehicles.Model.VehicleData;
import com.example.tdd.Vehicles.Model.VehicleRequest;
import com.example.tdd.Vehicles.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/vehicles")
@Validated
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleRequest> registerNewVehicle(
            @Valid
            @RequestBody VehicleRequest vehicleRequest) throws Exception {

        VehicleRequest vehicleRequest1 = vehicleService.addVehicle(vehicleRequest);
        return new ResponseEntity<>(vehicleRequest1, HttpStatus.CREATED);
    }


    @PostMapping("/vehicle")

    public ResponseEntity<VehicleData> addVehicle(@RequestBody VehicleData vehicleData) {
        return new ResponseEntity<>(vehicleData,HttpStatus.CREATED);
    }

//    @PostMapping("/vehicle")
//
//    public ResponseEntity<VehicleData> addVehicle(@Valid @RequestBody VehicleData vehicleData) {
//        return new ResponseEntity<>(vehicleData,HttpStatus.CREATED);
//    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleRequest> getVehicleDetial(
            @Valid @NonNull @Pattern(regexp="[(a-zA-Z0-9)]+", message="Enter valid data") @PathVariable String vehicleId)
            throws ConstraintViolationException {

        return new ResponseEntity(vehicleService.getVehicleData(vehicleId),HttpStatus.OK);
    }

}
