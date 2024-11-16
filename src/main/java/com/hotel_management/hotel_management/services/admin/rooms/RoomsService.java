package com.hotel_management.hotel_management.services.admin.rooms;

import java.util.Optional;

import com.hotel_management.hotel_management.Entity.Room;

public interface RoomsService {
    Room postRoom(Room room);
    RoomResponse getAllRooms(int pageNumber);
    Optional<Room> getSingleRoom(Integer id);
    Optional<Room> updateRoom(Room room);
    Optional<Room> deleteRoom(Integer id);
    ReservationResponse getAllReservations(Integer pageNumber);
}
