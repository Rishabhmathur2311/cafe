package com.hotel_management.hotel_management.services.admin.rooms;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hotel_management.hotel_management.Entity.Reservation;
import com.hotel_management.hotel_management.Entity.Room;
import com.hotel_management.hotel_management.Repository.ReservationRepo;
import com.hotel_management.hotel_management.Repository.RoomRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomsServiceImpl implements RoomsService {

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private ReservationRepo reservationRepo;

    public Room postRoom(Room room) {
        try{
            Room r=new Room();
            r.setName(room.getName());
            r.setPrice(room.getPrice());
            r.setAvailable(room.isAvailable());
            r.setType(room.getType());
            roomRepo.save(r);
            System.out.println(r);
            return r;
        }catch(Exception e){
            return null;
        }
    }

    public RoomResponse getAllRooms(int pageNumber) {
        Pageable pageable= PageRequest.of(pageNumber, 4);
        Page<Room> roomPage=roomRepo.findAll(pageable);

        List<Room>ans=roomPage.stream().toList();

        System.out.println(roomPage);
        RoomResponse rr=new RoomResponse();
        rr.setPageNumber(roomPage.getPageable().getPageNumber());
        rr.setTotalPages(roomPage.getTotalPages());
        rr.setRoomsList(roomPage.stream().map(Room::getRoom).collect(Collectors.toList()));
        System.out.println(rr);
        return rr;

    }

    public Optional<Room> getSingleRoom(Integer id) {
        Optional<Room> rr=roomRepo.findById(id);

        if(rr!=null){
            System.out.println(rr);
            return rr;
        }
        System.out.println("invalid room");
        return null;
    }

    public Optional<Room> updateRoom(Room room) {
        Optional<Room> rr=roomRepo.findById(room.getId());
        Room r=rr.get();

        if(rr!=null){
            r.setName(room.getName());
            r.setPrice(room.getPrice());
            r.setAvailable(room.isAvailable());
            r.setType(room.getType());
            roomRepo.save(r);
            return rr;
        }
        System.out.println("invalid room");
        return null;
    }

    @Override
    public Optional<Room> deleteRoom(Integer id) {
        Optional<Room>r=roomRepo.findById(id);
        if(r!=null){
            roomRepo.deleteById(id);
        }
        else{
            System.out.println("invalid room");
        }
        return r;
    }

    public ReservationResponse getAllReservations(Integer pageNumber){
        Pageable pageable= PageRequest.of(pageNumber, 4);
        Page<Reservation> roomPage=reservationRepo.findAll(pageable);

        List<Reservation>ans=roomPage.stream().toList();

        System.out.println(roomPage);
        ReservationResponse rr=new ReservationResponse();
        rr.setCurrentPage(roomPage.getPageable().getPageNumber());
        rr.setTotalPages(roomPage.getTotalPages());
        rr.setReservations(roomPage.stream().map(Reservation::getReservation).collect(Collectors.toList()));
        System.out.println(rr);
        return rr;
        
    }
}
