package lk.ijse.parkingspaceservice.dto;

import lk.ijse.parkingspaceservice.entity.SpaceStatus;
import lombok.Data;

@Data
public class StatusUpdateRequest {
    private SpaceStatus status;
}