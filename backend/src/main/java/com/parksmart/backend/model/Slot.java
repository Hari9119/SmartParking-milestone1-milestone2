package com.parksmart.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "slots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String slotNumber;

    @Enumerated(EnumType.STRING)
    private SlotStatus status;
}
