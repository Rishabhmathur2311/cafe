package com.hotel_management.hotel_management.Entity;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}
