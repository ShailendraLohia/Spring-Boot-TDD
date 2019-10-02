package com.example.tdd.Vehicles;

import com.example.tdd.Vehicles.Model.VehiclePrice;
import com.example.tdd.Vehicles.Repository.VehiclePriceRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class VehiclePriceRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private VehiclePriceRepository vehiclePriceRepository;
    private VehiclePrice vehiclePrice;
    @Before
    public void initialSetUp() {
        vehiclePrice=new VehiclePrice("Honda","2002",2000.0);
    }
    @Test
    public void testSaveMethod() throws Exception {
        testEntityManager.persistAndFlush(vehiclePrice);
        vehiclePrice=vehiclePriceRepository.findById("Honda").get();

        Assertions.assertThat(vehiclePrice.getPrice().equals(2000.0));
    }
}
