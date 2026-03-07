package com.WebApplication.service.Implementation;
import java.util.List;
import java.util.Optional;

import com.WebApplication.entity.Seats;
import com.WebApplication.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SeatServicesImpl {
    @Autowired
    private SeatRepository seatRepository;

    // 1. View all seats
    public List<Seats> getAllSeats() {
        return seatRepository.findAll();
    }

    // 2. Book a seat
    public String bookSeat(int seatNumber) {

        if(seatNumber < 1 || seatNumber > 60){
            return "Invalid seat number! Only 60 seats available.";
        }

        Optional<Seats> seat = seatRepository.findById(seatNumber);

        if (seat.isPresent()) {
            Seats s = seat.get();

            if (s.isBooked()) {
                return "Seat already booked!";
            }

            s.setBooked(true);
            seatRepository.save(s);

            return "Seat booked successfully!";
        }

        return "Seat not found!";
    }
    // 3. Delete seat booking (cancel booking)
    public String deleteSeat(int seatNumber) {

        if(seatNumber < 1 || seatNumber > 60){
            return "Invalid seat number!";
        }

        Optional<Seats> seat = seatRepository.findById(seatNumber);

        if (seat.isPresent()) {

            Seats s = seat.get();
            s.setBooked(false);
            seatRepository.save(s);

            return "Seat booking cancelled!";
        }

        return "Seat not found!";
    }
}
