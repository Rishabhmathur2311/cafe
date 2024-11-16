package com.hotel_management.hotel_management.services.admin.rooms;

import java.util.List;

import com.hotel_management.hotel_management.Entity.ReservationDto;

import lombok.Data;

@Data
public class ReservationResponse {
    private Integer totalPages;
    private Integer currentPage;

    private List<ReservationDto>reservations;
}
