package lk.ijse.parkingspaceservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ParkingSpaceRequest {

    @NotBlank(message = "Space code is required")
    private String spaceCode;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "City is required")
    private String city;

    @NotNull(message = "Price per hour is required")
    @Positive(message = "Price per hour must be greater than zero")
    private Double pricePerHour;

    @NotNull(message = "Owner ID is required")
    private Long ownerId;
}