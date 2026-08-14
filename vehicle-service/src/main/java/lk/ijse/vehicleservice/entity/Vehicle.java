package lk.ijse.vehicleservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String vehicleNumber; // e.g., CAB-1234

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    private String model;

    private Long userId; // Linked to User Service User ID

    @Enumerated(EnumType.STRING)
    private VehicleStatus status; // PARKED or OUT

    private LocalDateTime lastEntryTime;
    private LocalDateTime lastExitTime;
}