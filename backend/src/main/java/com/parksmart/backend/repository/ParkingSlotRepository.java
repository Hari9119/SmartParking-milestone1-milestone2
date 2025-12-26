package com.parksmart.backend.repository;

import com.parksmart.backend.model.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, String> {
}
