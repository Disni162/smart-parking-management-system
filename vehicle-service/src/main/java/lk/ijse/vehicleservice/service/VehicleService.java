package lk.ijse.vehicleservice.service;

import lk.ijse.vehicleservice.dto.VehicleRequest;
import lk.ijse.vehicleservice.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle registerVehicle(VehicleRequest request);
    Vehicle getVehicleById(Long id);
    Vehicle getVehicleByNumber(String vehicleNumber);
    List<Vehicle> getVehiclesByUserId(Long userId);
    List<Vehicle> getAllVehicles();
    Vehicle simulateEntry(String vehicleNumber);
    Vehicle simulateExit(String vehicleNumber);
}