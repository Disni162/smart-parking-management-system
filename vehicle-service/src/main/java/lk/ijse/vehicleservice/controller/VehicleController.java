package lk.ijse.vehicleservice.controller;

import lk.ijse.vehicleservice.dto.EntryExitRequest;
import lk.ijse.vehicleservice.dto.VehicleRequest;
import lk.ijse.vehicleservice.entity.Vehicle;
import lk.ijse.vehicleservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<Vehicle> registerVehicle(@RequestBody VehicleRequest request) {
        return ResponseEntity.ok(vehicleService.registerVehicle(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.getVehicleById(id));
    }

    @GetMapping("/number/{vehicleNumber}")
    public ResponseEntity<Vehicle> getVehicleByNumber(@PathVariable String vehicleNumber) {
        return ResponseEntity.ok(vehicleService.getVehicleByNumber(vehicleNumber));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Vehicle>> getVehiclesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(vehicleService.getVehiclesByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @PostMapping("/entry")
    public ResponseEntity<Vehicle> simulateEntry(@RequestBody EntryExitRequest request) {
        return ResponseEntity.ok(vehicleService.simulateEntry(request.getVehicleNumber()));
    }

    @PostMapping("/exit")
    public ResponseEntity<Vehicle> simulateExit(@RequestBody EntryExitRequest request) {
        return ResponseEntity.ok(vehicleService.simulateExit(request.getVehicleNumber()));
    }
}