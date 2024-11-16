package com.hotel_management.hotel_management.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel_management.hotel_management.Entity.User;
import com.hotel_management.hotel_management.Enum.UserRole;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    Optional<User> findByUserRole(UserRole role);
}
