package lk.ijse.parkingspaceservice.controller;

import jakarta.validation.Valid;
import lk.ijse.parkingspaceservice.dto.ParkingSpaceRequest;
import lk.ijse.parkingspaceservice.dto.StatusUpdateRequest;
import lk.ijse.parkingspaceservice.entity.ParkingSpace;
import lk.ijse.parkingspaceservice.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking-spaces")
public class ParkingSpaceController {

    @Autowired
    private ParkingSpaceService parkingSpaceService;

    @PostMapping
    public ResponseEntity<ParkingSpace> createSpace(
            @Valid @RequestBody ParkingSpaceRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(parkingSpaceService.createParkingSpace(request));
    }

    @GetMapping
    public ResponseEntity<List<ParkingSpace>> getAllSpaces() {
        return ResponseEntity.ok(
                parkingSpaceService.getAllSpaces()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpace> getSpaceById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                parkingSpaceService.getSpaceById(id)
        );
    }

    @GetMapping("/available/{city}")
    public ResponseEntity<List<ParkingSpace>> getAvailableSpacesByCity(
            @PathVariable String city) {

        return ResponseEntity.ok(
                parkingSpaceService.getAvailableSpacesByCity(city)
        );
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<ParkingSpace>> getSpacesByOwner(
            @PathVariable Long ownerId) {

        return ResponseEntity.ok(
                parkingSpaceService.getSpacesByOwner(ownerId)
        );
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ParkingSpace> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody StatusUpdateRequest request) {

        return ResponseEntity.ok(
                parkingSpaceService.updateSpaceStatus(
                        id,
                        request.getStatus()
                )
        );
    }

    @PutMapping("/{id}/reserve")
    public ResponseEntity<ParkingSpace> reserveSpace(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                parkingSpaceService.reserveSpace(id)
        );
    }

    @PutMapping("/{id}/release")
    public ResponseEntity<ParkingSpace> releaseSpace(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                parkingSpaceService.releaseSpace(id)
        );
    }
}