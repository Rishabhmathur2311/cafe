package com.hotel_management.hotel_management.Entity;

import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.hotel_management.hotel_management.Enum.ReservationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Long price;

    private ReservationStatus reservationStatus;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="room_id", nullable=false)
    @OnDelete(action=OnDeleteAction.CASCADE)
    private Room room;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="user_id", nullable=false)
    @OnDelete(action=OnDeleteAction.CASCADE)
    private User user;

    public ReservationDto getReservation(){
        ReservationDto r=new ReservationDto();

        r.setId(id);
        r.setCheckInDate(checkInDate);
        r.setCheckOutDate(checkOutDate);
        r.setPrice(price);
        r.setReservationStatus(reservationStatus);
        r.setUserId(user.getId());
        r.setRoomId(room.getId());
        r.setUsername(user.getName());
        r.setRoomType(room.getType());
        r.setRoomName(room.getName());

        return r;
    }
}
