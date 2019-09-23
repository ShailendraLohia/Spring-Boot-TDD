package com.example.tdd.Vehicles;

import com.example.tdd.Vehicles.Exception.VehicleNotFoundException;
import com.example.tdd.Vehicles.Model.VehicleData;
import com.example.tdd.Vehicles.Model.VehicleRequest;
import com.example.tdd.Vehicles.Repository.VehicleRepository;
import com.example.tdd.Vehicles.Service.VehicleService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;


//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
public class VehicleServiceTest {

//    @Autowired
    private VehicleService vehicleService;

    @MockBean
    private VehicleRepository mockVehicleRepository;

    @Rule
    public ExpectedException expectedException= ExpectedException.none();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        vehicleService = new VehicleService(mockVehicleRepository);
    }

    @Test
    public void testAddVehicle() throws Exception {

        VehicleData vehicleData = new VehicleData("123abc","Honda","2010",2000.00);
        VehicleRequest vehicleRequest = new VehicleRequest(vehicleData);

//        doNothing().when(mockVehicleRepository.save(any()));
        vehicleService.addVehicle(vehicleRequest);

        verify(mockVehicleRepository).save(vehicleRequest.getVehicles());
        //Assertions.assertThat(vehicleService.addVehicle(vehicleRequest).equals(vehicleRequest));

    }
    @Test
    public void testGetVehicleMethod() throws Exception {
        VehicleData vehicleData = new VehicleData("123abc","Honda","2010",2000.00);
        VehicleRequest vehicleRequest = new VehicleRequest(vehicleData);

        when(mockVehicleRepository.findById(anyString())).thenReturn(Optional.of(vehicleRequest.getVehicles()));
//
         vehicleRequest= vehicleService.getVehicleData("123abc");
        Assertions.assertThat(vehicleRequest.getVehicles().getVIN().equals("123abc"));
    }

    @Test
    public void testGetVehicleMethod_NotFoundException() throws Exception {

        when(mockVehicleRepository.findById(anyString())).thenReturn(Optional.empty());
        expectedException.expect(VehicleNotFoundException.class);

        vehicleService.getVehicleData("123abc");

    }
}
