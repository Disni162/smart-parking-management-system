package lk.ijse.vehicleservice.service.impl;

import lk.ijse.vehicleservice.dto.VehicleRequest;
import lk.ijse.vehicleservice.entity.Vehicle;
import lk.ijse.vehicleservice.entity.VehicleStatus;
import lk.ijse.vehicleservice.repository.VehicleRepository;
import lk.ijse.vehicleservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle registerVehicle(VehicleRequest request) {
        if (vehicleRepository.findByVehicleNumber(request.getVehicleNumber()).isPresent()) {
            throw new RuntimeException("Vehicle number already registered!");
        }

        Vehicle vehicle = Vehicle.builder()
                .vehicleNumber(request.getVehicleNumber())
                .vehicleType(request.getVehicleType())
                .model(request.getModel())
                .userId(request.getUserId())
                .status(VehicleStatus.OUT)
                .build();

        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + id));
    }

    @Override
    public Vehicle getVehicleByNumber(String vehicleNumber) {
        return vehicleRepository.findByVehicleNumber(vehicleNumber)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with Number: " + vehicleNumber));
    }

    @Override
    public List<Vehicle> getVehiclesByUserId(Long userId) {
        return vehicleRepository.findByUserId(userId);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle simulateEntry(String vehicleNumber) {
        Vehicle vehicle = getVehicleByNumber(vehicleNumber);
        if (vehicle.getStatus() == VehicleStatus.PARKED) {
            throw new RuntimeException("Vehicle is already parked inside!");
        }
        vehicle.setStatus(VehicleStatus.PARKED);
        vehicle.setLastEntryTime(LocalDateTime.now());
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle simulateExit(String vehicleNumber) {
        Vehicle vehicle = getVehicleByNumber(vehicleNumber);
        if (vehicle.getStatus() == VehicleStatus.OUT) {
            throw new RuntimeException("Vehicle is already outside!");
        }
        vehicle.setStatus(VehicleStatus.OUT);
        vehicle.setLastExitTime(LocalDateTime.now());
        return vehicleRepository.save(vehicle);
    }
}