package com.example.tdd.Twitter.Vehicles;

import com.example.tdd.Vehicles.Model.VehicleData;
import com.example.tdd.Vehicles.Model.VehicleRequest;
import com.example.tdd.Vehicles.Service.VehicleService;
import com.example.tdd.Vehicles.Controller.VehicleController;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private VehicleService vehicleService;

    @Autowired
    private ObjectMapper objectMapper;

//    @Rule
//    public ExpectedException expectedException = ExpectedException.none();

    private JacksonTester<VehicleRequest> jacksonTester;


    @Before
    public void setUpMock() {
        JacksonTester.initFields(this,objectMapper);
    }

    @Test
    public void testRegisterNewVehicle() throws Exception {

        VehicleData vehicleData = new VehicleData("123abc","Honda","2010",2000.00);
        VehicleRequest vehicleRequest = new VehicleRequest(vehicleData);

        given(vehicleService.addVehicle(any())).willReturn(vehicleRequest);

        String vehicleJson= jacksonTester.write(vehicleRequest).getJson();

        mvc.perform(post("/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vehicleJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.vehicles.vin", Matchers.is("123abc")))
                .andExpect(jsonPath("$.vehicles.model", Matchers.is("Honda")));
    }

    @Test
    public void testInvalidVehicleObject() throws Exception {

        VehicleData vehicleData = new VehicleData("","Honda","2010",2000.00);
        VehicleRequest vehicleRequest = new VehicleRequest(vehicleData);

        //given(vehicleService.addVehicle(any())).willThrow(new ValidationException("Exception"));

        String vehicleJson= jacksonTester.write(vehicleRequest).getJson();

        mvc.perform(post("/vehicles/vehicle")
                .content(vehicleJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

}
