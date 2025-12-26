package com.parksmart.backend.controller;

import com.parksmart.backend.model.*;
import com.parksmart.backend.repository.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin("*")
public class BookingController {

    private final BookingRepository bookingRepo;
    private final ParkingSlotRepository slotRepo;

    public BookingController(BookingRepository bookingRepo,
                             ParkingSlotRepository slotRepo) {
        this.bookingRepo = bookingRepo;
        this.slotRepo = slotRepo;
    }

    @PostMapping("/{slotId}")
    public String bookSlot(@PathVariable String slotId,
                           @RequestParam String username) {

        ParkingSlot slot = slotRepo.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        if (slot.getStatus() == SlotStatus.BOOKED)
            return "SLOT_ALREADY_BOOKED";

        slot.setStatus(SlotStatus.BOOKED);
        slotRepo.save(slot);

        Booking booking = new Booking();
        booking.setUsername(username);
        booking.setSlot(slot);
        booking.setStartTime(LocalDateTime.now());
        booking.setEndTime(LocalDateTime.now().plusHours(1));
        booking.setStatus("BOOKED");

        bookingRepo.save(booking);

        return "BOOKING_SUCCESS";
    }

    @GetMapping("/user/{username}")
    public List<Booking> userBookings(@PathVariable String username) {
        return bookingRepo.findByUsername(username);
    }

    @DeleteMapping("/{id}")
    public void cancel(@PathVariable Long id) {
        bookingRepo.deleteById(id);
    }
}
