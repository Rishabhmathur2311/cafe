package com.hotel_management.hotel_management.Entity;

import java.time.LocalDate;

import com.hotel_management.hotel_management.Enum.ReservationStatus;

import lombok.Data;

@Data
public class ReservationDto {

    private Long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Long price;

    private ReservationStatus reservationStatus;

    private Integer roomId;

    private String roomType;

    private String roomName;

    private Long userId;

    private String username;
}
