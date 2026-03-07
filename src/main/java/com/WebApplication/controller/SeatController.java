package com.WebApplication.controller;

import java.util.List;
import com.WebApplication.entity.Seats;
import com.WebApplication.service.Implementation.SeatServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    private SeatServicesImpl seatService;

    // 1. See seat details
    @GetMapping
    public List<Seats> getSeats() {
        return seatService.getAllSeats();
    }

    // 2. Book a seat
    @PostMapping("/book/{seatNumber}")
    public String bookSeat(@PathVariable int seatNumber) {
        return seatService.bookSeat(seatNumber);
    }

    // 3. Delete seat booking
    @DeleteMapping("/delete/{seatNumber}")
    public String deleteSeat(@PathVariable int seatNumber) {
        return seatService.deleteSeat(seatNumber);
    }
}