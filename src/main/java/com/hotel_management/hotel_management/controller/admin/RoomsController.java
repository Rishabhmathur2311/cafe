package com.hotel_management.hotel_management.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel_management.hotel_management.Entity.Room;
import com.hotel_management.hotel_management.services.admin.rooms.RoomsService;
import com.hotel_management.hotel_management.services.auth.AuthService;
import com.hotel_management.hotel_management.services.customer.booking.BookingService;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RoomsController {
    @Autowired
    private RoomsService roomsService;

    @Autowired
    private AuthService authService;

    @Autowired
    private BookingService bookingService;

    @PostMapping("/room")
    public Room postRoom(@RequestBody Room room) {

        try{
            System.out.println(room);
            return roomsService.postRoom(room);
        } catch (EntityExistsException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/rooms/{pageNumber}")
    public ResponseEntity<?> getRoomList(@PathVariable int pageNumber) {
        return ResponseEntity.ok(roomsService.getAllRooms(pageNumber));
    }

    @GetMapping("/singleRoom/{id}")
    public ResponseEntity<?> getSingleRoom(@PathVariable Integer id) {
        return ResponseEntity.ok(roomsService.getSingleRoom(id));
    }

    @PostMapping("/singleRoom/edit/{id}")
    public ResponseEntity<?> updateSingleRoom(@RequestBody Room room) {
        return ResponseEntity.ok(roomsService.updateRoom(room));
    }

    @DeleteMapping("/singleRoom/delete/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable Integer id) {
        System.out.println(id);
        return ResponseEntity.ok(roomsService.deleteRoom(id));
    }

    @GetMapping("getAllReservations/{pageNumber}")
    public ResponseEntity<?> getReservations(@PathVariable Integer pageNumber){
        return ResponseEntity.ok(roomsService.getAllReservations(pageNumber));
    }

    @PutMapping("/changeStatus/{id}")
    public ResponseEntity<?> changeStaus(@PathVariable Long id, @RequestBody Integer status){
        System.out.println(status);
        return ResponseEntity.ok(bookingService.changeStatus(status, id));
    }
}
