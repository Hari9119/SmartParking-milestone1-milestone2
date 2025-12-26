package com.parksmart.backend.repository;

import com.parksmart.backend.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUsername(String username);
}
