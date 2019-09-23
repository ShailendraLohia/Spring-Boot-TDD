package com.example.tdd.Vehicles;

import com.example.tdd.Vehicles.Model.VehicleData;
import com.example.tdd.Vehicles.Repository.VehicleRepository;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
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
public class VehicleRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    public void testFindByIdMethod() throws Exception {
         entityManager.persist(
                new VehicleData("123abc","Honda","2010",2000.00));

        VehicleData vehicleData= vehicleRepository.findById("123abc").get();

        Assertions.assertThat(vehicleData.getVIN().equals("123abc"));

    }
    @Ignore
    @Test
    public void testFindByIdMethod2() throws Exception {
//         entityManager.persist(
//                new VehicleData("123abc","Honda","2010",2000.00));

        VehicleData vehicleData= vehicleRepository.findById("123abc").get();
        entityManager.flush();

        Assertions.assertThat(vehicleData.getVIN().equals("123abc"));
        //vehicleRepository.findById("123abc").get();

    }
}
