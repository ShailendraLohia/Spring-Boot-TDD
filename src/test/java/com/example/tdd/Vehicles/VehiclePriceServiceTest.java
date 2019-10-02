package com.example.tdd.Vehicles;

import com.example.tdd.Vehicles.Model.VehiclePrice;
import com.example.tdd.Vehicles.Model.VehiclePriceDetails;
import com.example.tdd.Vehicles.Repository.VehiclePriceRepository;
import com.example.tdd.Vehicles.Service.VehiclePriceService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class VehiclePriceServiceTest {
    private VehiclePriceService vehiclePriceService;
    @MockBean
    private VehiclePriceRepository mockVehiclePriceRepository;
    private VehiclePrice vehiclePrice;
    private VehiclePriceDetails vehiclePriceDetails;

    @Before
    public void initialSetUp() {
        vehiclePrice=new VehiclePrice("Honda","2002",2000.0);
        vehiclePriceDetails=new VehiclePriceDetails(vehiclePrice);

        vehiclePriceService=new VehiclePriceService(mockVehiclePriceRepository);
    }

    @Test
    public void testAddVehiclePriceData() throws Exception {
        when(mockVehiclePriceRepository.save(any())).thenReturn(vehiclePrice);

        Assertions.assertThat(vehiclePriceService.addVehiclePriceData(vehiclePriceDetails).equals("VehiclePriceAdded"));


    }
}
