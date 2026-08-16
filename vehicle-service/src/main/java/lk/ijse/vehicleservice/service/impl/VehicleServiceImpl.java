package lk.ijse.vehicleservice.service.impl;

import lk.ijse.common.exception.BadRequestException;
import lk.ijse.common.exception.ResourceNotFoundException;
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

        if (request == null) {
            throw new BadRequestException(
                    "Vehicle registration request cannot be null"
            );
        }

        if (request.getVehicleNumber() == null ||
                request.getVehicleNumber().isBlank()) {
            throw new BadRequestException(
                    "Vehicle number cannot be empty"
            );
        }

        if (request.getVehicleType() == null) {
            throw new BadRequestException(
                    "Vehicle type cannot be null"
            );
        }

        if (request.getModel() == null ||
                request.getModel().isBlank()) {
            throw new BadRequestException(
                    "Vehicle model cannot be empty"
            );
        }

        if (request.getUserId() == null) {
            throw new BadRequestException(
                    "User ID cannot be null"
            );
        }

        if (vehicleRepository.findByVehicleNumber(
                request.getVehicleNumber()).isPresent()) {
            throw new BadRequestException(
                    "Vehicle number already registered: "
                            + request.getVehicleNumber()
            );
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

        if (id == null) {
            throw new BadRequestException(
                    "Vehicle ID cannot be null"
            );
        }

        return vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vehicle not found with ID: " + id
                        )
                );
    }

    @Override
    public Vehicle getVehicleByNumber(String vehicleNumber) {

        if (vehicleNumber == null ||
                vehicleNumber.isBlank()) {
            throw new BadRequestException(
                    "Vehicle number cannot be empty"
            );
        }

        return vehicleRepository.findByVehicleNumber(vehicleNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vehicle not found with Number: "
                                        + vehicleNumber
                        )
                );
    }

    @Override
    public List<Vehicle> getVehiclesByUserId(Long userId) {

        if (userId == null) {
            throw new BadRequestException(
                    "User ID cannot be null"
            );
        }

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
            throw new BadRequestException(
                    "Vehicle is already parked inside!"
            );
        }

        vehicle.setStatus(VehicleStatus.PARKED);
        vehicle.setLastEntryTime(LocalDateTime.now());

        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle simulateExit(String vehicleNumber) {

        Vehicle vehicle = getVehicleByNumber(vehicleNumber);

        if (vehicle.getStatus() == VehicleStatus.OUT) {
            throw new BadRequestException(
                    "Vehicle is already outside!"
            );
        }

        vehicle.setStatus(VehicleStatus.OUT);
        vehicle.setLastExitTime(LocalDateTime.now());

        return vehicleRepository.save(vehicle);
    }
}