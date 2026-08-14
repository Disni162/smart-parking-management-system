package lk.ijse.userservice.dto;

import lk.ijse.userservice.entity.UserRole;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String phone;
    private UserRole role;
}