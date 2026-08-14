package lk.ijse.vehicleservice.dto;

import lk.ijse.vehicleservice.entity.VehicleType;
import lombok.Data;

@Data
public class VehicleRequest {
    private String vehicleNumber;
    private VehicleType vehicleType;
    private String model;
    private Long userId;
}