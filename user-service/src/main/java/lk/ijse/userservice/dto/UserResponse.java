package lk.ijse.userservice.dto;

import lk.ijse.userservice.entity.UserRole;
import lombok.Data;

@Data
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private UserRole role;

    public UserResponse(
            Long id,
            String name,
            String email,
            String phone,
            UserRole role) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }
}