package com.example.tdd.Vehicles.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequest {

    @Valid
    //@NotNull(message = "cannot be null")
//    @NotEmpty(message= "cannot be empty")
    private VehicleData vehicles;

}
