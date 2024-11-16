package com.hotel_management.hotel_management.services.auth;

import com.hotel_management.hotel_management.Entity.User;


public interface AuthService {
    public User createAdmin(User user);
    public User createCustomer(User user);
    public User getUserByEmail(String email);
}
