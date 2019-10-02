package com.example.tdd.Vehicles;

import com.example.tdd.Vehicles.Model.VehicleData;
import com.example.tdd.Vehicles.Model.VehicleRequest;
import com.example.tdd.Vehicles.Repository.VehicleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VehicleIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VehicleRepository mockVehicleRepository;
    @Autowired
    private ObjectMapper objectMapper;
    private VehicleData vehicleData;
    private VehicleRequest vehicleRequest;
    private JacksonTester<VehicleRequest> jacksonTester;

    @Before
    public void setUp() {
         vehicleData = new VehicleData("123abc","Honda","2010",2000.00);
         vehicleRequest = new VehicleRequest(vehicleData);
         JacksonTester.initFields(this,objectMapper);

        //verify(mockVehicleRepository.findById("123abc")).
    }

    @Test
    public void findVehicle() throws Exception {

        given(mockVehicleRepository.findById("123abc")).willReturn(Optional.of(vehicleData));
        mockMvc.perform(get("/vehicles/123abc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vehicles.vin", Matchers.is("123abc")))
                .andExpect(jsonPath("$.vehicles.model", Matchers.is("Honda")));

        verify(mockVehicleRepository, times(1)).findById("123abc");
    }

    @Test
    public void addVehicle() throws Exception {
        given(mockVehicleRepository.save(vehicleData)).willReturn(vehicleData);
        String data=jacksonTester.write(vehicleRequest).getJson();
        mockMvc.perform(post("/vehicles")
                .content(data)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.vehicles.vin",Matchers.is("123abc")))
                .andExpect(jsonPath("$.vehicles.model",Matchers.is("Honda")));

        verify(mockVehicleRepository,times(1)).save(vehicleData);
    }

}
