package com.parksmart.backend.controller;

import com.parksmart.backend.model.*;
import com.parksmart.backend.repository.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import com.parksmart.backend.model.ParkingSlot;


@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    private final ParkingSlotRepository slotRepo;
    private final BookingRepository bookingRepo;

    public AdminController(ParkingSlotRepository slotRepo, BookingRepository bookingRepo) {
        this.slotRepo = slotRepo;
        this.bookingRepo = bookingRepo;
    }

    @GetMapping("/stats")
    public Map<String, Long> stats() {
        Map<String, Long> map = new HashMap<>();
        long total = slotRepo.count();
        long booked = bookingRepo.count();

        map.put("TOTAL", total);
        map.put("BOOKED", booked);
        map.put("AVAILABLE", total - booked);

        return map;
    }
    // GET ALL SLOTS
      @GetMapping("/slots")
      public List<ParkingSlot> getAllSlots() {
       return slotRepo.findAll();
}


    @PostMapping("/slot/{slotId}")
    public String addSlot(@PathVariable String slotId) {
        slotRepo.save(new ParkingSlot(slotId, SlotStatus.AVAILABLE));
        return "SLOT_ADDED";
    }

    @DeleteMapping("/slot/{slotId}")
    public String deleteSlot(@PathVariable String slotId) {
        slotRepo.deleteById(slotId);
        return "SLOT_DELETED";
    }


}
