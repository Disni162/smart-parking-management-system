package lk.ijse.parkingspaceservice.repository;

import lk.ijse.parkingspaceservice.entity.ParkingSpace;
import lk.ijse.parkingspaceservice.entity.SpaceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {
    Optional<ParkingSpace> findBySpaceCode(String spaceCode);
    List<ParkingSpace> findByCityAndStatus(String city, SpaceStatus status);
    List<ParkingSpace> findByOwnerId(Long ownerId);
}