package lk.ijse.parkingspaceservice.service.impl;

import lk.ijse.parkingspaceservice.dto.ParkingSpaceRequest;
import lk.ijse.parkingspaceservice.entity.ParkingSpace;
import lk.ijse.parkingspaceservice.entity.SpaceStatus;
import lk.ijse.parkingspaceservice.repository.ParkingSpaceRepository;
import lk.ijse.parkingspaceservice.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSpaceServiceImpl implements ParkingSpaceService {

    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    @Override
    public ParkingSpace createParkingSpace(ParkingSpaceRequest request) {
        if (parkingSpaceRepository.findBySpaceCode(request.getSpaceCode()).isPresent()) {
            throw new RuntimeException("Space Code already exists!");
        }

        ParkingSpace space = ParkingSpace.builder()
                .spaceCode(request.getSpaceCode())
                .location(request.getLocation())
                .city(request.getCity())
                .pricePerHour(request.getPricePerHour())
                .ownerId(request.getOwnerId())
                .status(SpaceStatus.AVAILABLE)
                .build();

        return parkingSpaceRepository.save(space);
    }

    @Override
    public ParkingSpace getSpaceById(Long id) {
        return parkingSpaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking Space not found with ID: " + id));
    }

    @Override
    public ParkingSpace getSpaceByCode(String spaceCode) {
        return parkingSpaceRepository.findBySpaceCode(spaceCode)
                .orElseThrow(() -> new RuntimeException("Parking Space not found with Code: " + spaceCode));
    }

    @Override
    public List<ParkingSpace> getAllSpaces() {
        return parkingSpaceRepository.findAll();
    }

    @Override
    public List<ParkingSpace> getAvailableSpacesByCity(String city) {
        return parkingSpaceRepository.findByCityAndStatus(city, SpaceStatus.AVAILABLE);
    }

    @Override
    public List<ParkingSpace> getSpacesByOwner(Long ownerId) {
        return parkingSpaceRepository.findByOwnerId(ownerId);
    }

    @Override
    public ParkingSpace updateSpaceStatus(Long id, SpaceStatus status) {
        ParkingSpace space = getSpaceById(id);
        space.setStatus(status);
        return parkingSpaceRepository.save(space);
    }

    @Override
    public ParkingSpace reserveSpace(Long id) {
        ParkingSpace space = getSpaceById(id);
        if (space.getStatus() != SpaceStatus.AVAILABLE) {
            throw new RuntimeException("Parking space is not available for reservation!");
        }
        space.setStatus(SpaceStatus.RESERVED);
        return parkingSpaceRepository.save(space);
    }

    @Override
    public ParkingSpace releaseSpace(Long id) {
        ParkingSpace space = getSpaceById(id);
        space.setStatus(SpaceStatus.AVAILABLE);
        return parkingSpaceRepository.save(space);
    }
}