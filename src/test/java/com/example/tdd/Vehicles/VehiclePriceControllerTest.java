package com.example.tdd.Vehicles;

import com.example.tdd.Vehicles.Controller.VehiclePriceController;
import com.example.tdd.Vehicles.Exception.VehicleNotFoundException;
import com.example.tdd.Vehicles.Exception.VehiclePriceNotFoundException;
import com.example.tdd.Vehicles.Model.VehiclePrice;
import com.example.tdd.Vehicles.Model.VehiclePriceDetails;
import com.example.tdd.Vehicles.Service.VehiclePriceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(VehiclePriceController.class)
public class VehiclePriceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private JacksonTester<VehiclePriceDetails> jacksonTester;

    @MockBean
    private VehiclePriceService vehiclePriceService;

    @Before
    public void setUp() {
        JacksonTester.initFields(this,objectMapper);
    }

    @Test
    public void testAddVehiclePrice() throws Exception {
        VehiclePrice vehiclePrice=new VehiclePrice("Honda","2002",2000.0);
        VehiclePriceDetails vehiclePriceDetails=new VehiclePriceDetails(vehiclePrice);
        given(vehiclePriceService.addVehiclePriceData(any())).willReturn("VehiclePriceAdded");
        String data=jacksonTester.write(vehiclePriceDetails).getJson();
        mockMvc.perform(post("/price")
                .content(data)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string("VehiclePriceAdded"));
    }

    @Test
    public void testAddVehiclePrice_validationException() throws Exception {
        VehiclePrice vehiclePrice=new VehiclePrice("","2002",2000.0);
        VehiclePriceDetails vehiclePriceDetails=new VehiclePriceDetails(vehiclePrice);
        given(vehiclePriceService.addVehiclePriceData(any())).willReturn("VehiclePriceAdded");
        String data=jacksonTester.write(vehiclePriceDetails).getJson();
        mockMvc.perform(post("/price")
                .content(data)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test // TODO API not found error not working
    public void testAddVehicle_NotFoundException() throws Exception {
        VehiclePrice vehiclePrice=new VehiclePrice("","2002",2000.0);
        VehiclePriceDetails vehiclePriceDetails=new VehiclePriceDetails(vehiclePrice);
        given(vehiclePriceService.addVehiclePriceData(any())).willReturn("VehiclePriceAdded");
        String data=jacksonTester.write(vehiclePriceDetails).getJson();

        mockMvc.perform(post("/price/123")
                .content(data)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
               // .andExpect(jsonPath("$.status").value("NOT_FOUND"));
    }
}
