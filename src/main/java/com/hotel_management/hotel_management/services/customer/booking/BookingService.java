package com.hotel_management.hotel_management.services.customer.booking;

import com.hotel_management.hotel_management.Entity.ReservationDto;
import com.hotel_management.hotel_management.Enum.ReservationStatus;

public interface BookingService {
    boolean postReservation(ReservationDto reservationDto);
    ReservationStatus changeStatus(Integer status, Long id);
}
