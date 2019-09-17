package com.example.tdd;

import com.example.tdd.Twitter.Model.Tweet;
import com.example.tdd.Twitter.Repository.TwitterRepository;
import com.example.tdd.Vehicles.Model.VehicleData;
import com.example.tdd.Vehicles.Repository.VehicleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

@SpringBootApplication
public class TDDApplication {
    public static void main(String[] args) {
        SpringApplication.run(TDDApplication.class,args);

    }
//    @Bean
//    @Order(1) //Order with lower number has higher precedence
//    CommandLineRunner runner1(VehicleRepository repository) {
//        return args -> {
//            repository.save(new VehicleData("123abc","Honda","2010",2000.00));
//        };
//
//    }
//
//    @Bean
//    @Order(2)
//    CommandLineRunner runner2(TwitterRepository repository) {
//        return args -> {
//            repository.save(new Tweet("123","Hello, There!"));
//        };
//    }
}
