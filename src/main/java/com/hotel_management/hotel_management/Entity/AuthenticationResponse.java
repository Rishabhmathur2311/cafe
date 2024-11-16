package com.hotel_management.hotel_management.Entity;

import com.hotel_management.hotel_management.Enum.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private String jwt;
    private Long userId;
    private UserRole userRole;
}
