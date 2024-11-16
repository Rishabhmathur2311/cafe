package com.hotel_management.hotel_management.Repository;

import com.hotel_management.hotel_management.Entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepo extends JpaRepository<Room, Integer> {
    Page<Room> findByAvailable(boolean available, Pageable pageable);
}
