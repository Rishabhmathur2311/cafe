package com.hotel_management.hotel_management.services.customer.rooms;

import com.hotel_management.hotel_management.Entity.Room;
import com.hotel_management.hotel_management.Repository.RoomRepo;
import com.hotel_management.hotel_management.services.admin.rooms.RoomResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    RoomResponse getAvailableRooms(int pageNumber);
}
