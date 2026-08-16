package lk.ijse.parkingspaceservice;

import lk.ijse.common.exception.GlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(GlobalExceptionHandler.class)

@SpringBootApplication
public class ParkingSpaceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkingSpaceServiceApplication.class, args);
    }
}