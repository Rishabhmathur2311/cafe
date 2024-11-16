package com.hotel_management.hotel_management.services.admin.rooms;

import java.util.List;

import com.hotel_management.hotel_management.Entity.RoomDto;

import lombok.Data;

@Data
public class RoomResponse {

    private List<RoomDto> roomsList;

    private Integer totalPages;

    private Integer pageNumber;



}
