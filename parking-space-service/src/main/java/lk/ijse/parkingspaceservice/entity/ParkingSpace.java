package lk.ijse.parkingspaceservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "parking_spaces")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkingSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String spaceCode; // e.g., A-101

    private String location;  // e.g., Zone A
    private String city;      // e.g., Colombo
    private Double pricePerHour;
    private Long ownerId;     // Linked to User Service (PARKING_OWNER)

    @Enumerated(EnumType.STRING)
    private SpaceStatus status;
}