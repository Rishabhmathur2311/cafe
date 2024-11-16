package com.hotel_management.hotel_management.services.customer.booking;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel_management.hotel_management.Entity.Reservation;
import com.hotel_management.hotel_management.Entity.ReservationDto;
import com.hotel_management.hotel_management.Entity.Room;
import com.hotel_management.hotel_management.Entity.User;
import com.hotel_management.hotel_management.Enum.ReservationStatus;
import com.hotel_management.hotel_management.Repository.ReservationRepo;
import com.hotel_management.hotel_management.Repository.RoomRepo;
import com.hotel_management.hotel_management.Repository.UserRepo;
import com.hotel_management.hotel_management.services.admin.rooms.ReservationResponse;

@Service
public class BookingServiceImpl  implements BookingService{
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private ReservationRepo reservationRepo;

    public boolean postReservation(ReservationDto reservationDto){
        Optional<User>optionalUser=userRepo.findById(reservationDto.getUserId());
        Optional<Room>optionalRoom=roomRepo.findById(reservationDto.getRoomId());

        if(optionalRoom.isPresent() && optionalUser.isPresent()){
            Reservation reservation=new Reservation();

            reservation.setRoom(optionalRoom.get());
            reservation.setUser(optionalUser.get());
            reservation.setCheckInDate(reservationDto.getCheckInDate());
            reservation.setCheckOutDate(reservationDto.getCheckOutDate());
            reservation.setReservationStatus(ReservationStatus.PENDING);

            Long days=ChronoUnit.DAYS.between(reservationDto.getCheckInDate(),reservationDto.getCheckOutDate());
            reservation.setPrice(optionalRoom.get().getPrice()*days);

            System.out.print(reservation);

            reservationRepo.save(reservation);
            System.out.print(reservation);
            return true;
        }

        return false;
    }

    public ReservationStatus changeStatus(Integer status, Long id){
        Optional<Reservation> reservation=reservationRepo.findById(id);
        if(reservation.isPresent()){
            
            reservationRepo.deleteById(id);
            if(status==1){
                reservation.get().setReservationStatus(ReservationStatus.APPROVED);
                reservationRepo.save(reservation.get());
            }
            else{
                reservation.get().setReservationStatus(ReservationStatus.REJECTED);
            }
            System.out.println(reservation);
            
            return reservation.get().getReservationStatus();
        }
        return null;
    }
}
