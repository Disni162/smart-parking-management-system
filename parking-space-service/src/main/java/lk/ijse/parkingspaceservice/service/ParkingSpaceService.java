package lk.ijse.parkingspaceservice.service;

import lk.ijse.parkingspaceservice.dto.ParkingSpaceRequest;
import lk.ijse.parkingspaceservice.entity.ParkingSpace;
import lk.ijse.parkingspaceservice.entity.SpaceStatus;

import java.util.List;

public interface ParkingSpaceService {
    ParkingSpace createParkingSpace(ParkingSpaceRequest request);
    ParkingSpace getSpaceById(Long id);
    ParkingSpace getSpaceByCode(String spaceCode);
    List<ParkingSpace> getAllSpaces();
    List<ParkingSpace> getAvailableSpacesByCity(String city);
    List<ParkingSpace> getSpacesByOwner(Long ownerId);
    ParkingSpace updateSpaceStatus(Long id, SpaceStatus status);
    ParkingSpace reserveSpace(Long id);
    ParkingSpace releaseSpace(Long id);
}