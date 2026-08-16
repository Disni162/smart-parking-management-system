package lk.ijse.vehicleservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lk.ijse.vehicleservice.entity.VehicleType;
import lombok.Data;

@Data
public class VehicleRequest {

    @NotBlank(message = "Vehicle number is required")
    private String vehicleNumber;

    @NotNull(message = "Vehicle type is required")
    private VehicleType vehicleType;

    @NotBlank(message = "Vehicle model is required")
    private String model;

    @NotNull(message = "User ID is required")
    @Positive(message = "User ID must be greater than zero")
    private Long userId;
}