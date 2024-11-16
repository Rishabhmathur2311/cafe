package com.hotel_management.hotel_management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel_management.hotel_management.Entity.Reservation;

public interface ReservationRepo extends JpaRepository<Reservation, Long>{
    
}
