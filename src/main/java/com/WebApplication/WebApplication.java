package com.WebApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

//    @Bean
//    CommandLineRunner initSeats(SeatRepository seatRepository) {
//        return args -> {
//            for (int i = 1; i <= 60; i++) {
//                if (!seatRepository.existsById(i)) {
//                    seatRepository.save(new Seats(i, false));
//                }
//            }
//        };
//    }
}