package com.WebApplication.repository;
import com.WebApplication.entity.Seats;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SeatRepository extends JpaRepository<Seats, Integer> {

}
