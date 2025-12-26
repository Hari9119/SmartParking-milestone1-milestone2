package com.parksmart.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "parking_slots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSlot {

    @Id
    private String slotId;

    @Enumerated(EnumType.STRING)
    private SlotStatus status;
}
