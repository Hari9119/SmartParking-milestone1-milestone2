package com.parksmart.backend.config;

import com.parksmart.backend.model.ParkingSlot;
import com.parksmart.backend.model.SlotStatus;
import com.parksmart.backend.repository.ParkingSlotRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private final ParkingSlotRepository repo;

    public DataLoader(ParkingSlotRepository repo) {
        this.repo = repo;
    }

    @PostConstruct
    public void loadSlots() {
        if (repo.count() == 0) {
            repo.save(new ParkingSlot("A1", SlotStatus.AVAILABLE));
            repo.save(new ParkingSlot("A2", SlotStatus.AVAILABLE));
            repo.save(new ParkingSlot("A3", SlotStatus.AVAILABLE));
            repo.save(new ParkingSlot("A4", SlotStatus.AVAILABLE));
            repo.save(new ParkingSlot("B1", SlotStatus.AVAILABLE));
            repo.save(new ParkingSlot("B2", SlotStatus.AVAILABLE));
        }
    }
}
