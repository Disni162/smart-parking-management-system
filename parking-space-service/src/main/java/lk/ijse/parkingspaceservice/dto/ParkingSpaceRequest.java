package lk.ijse.parkingspaceservice.dto;

import lombok.Data;

@Data
public class ParkingSpaceRequest {
    private String spaceCode;
    private String location;
    private String city;
    private Double pricePerHour;
    private Long ownerId;
}