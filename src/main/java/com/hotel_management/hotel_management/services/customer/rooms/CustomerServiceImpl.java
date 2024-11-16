package com.hotel_management.hotel_management.services.customer.rooms;

import com.hotel_management.hotel_management.Entity.Room;
import com.hotel_management.hotel_management.Repository.RoomRepo;
import com.hotel_management.hotel_management.services.admin.rooms.RoomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    RoomRepo roomRepo;
    @Qualifier("handlerExceptionResolver")
    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    @Override
    public RoomResponse getAvailableRooms(int pageNumber) {
        System.out.println("Inside getAvailableRooms method");
        Pageable pageable= PageRequest.of(pageNumber, 4);
        Page<Room> roomPage=roomRepo.findByAvailable(true, pageable);

        List<Room> ans=roomPage.stream().toList();

        System.out.println(roomPage);
        RoomResponse rr=new RoomResponse();
        rr.setPageNumber(roomPage.getPageable().getPageNumber());
        rr.setTotalPages(roomPage.getTotalPages());
        rr.setRoomsList(roomPage.stream().map(Room::getRoom).collect(Collectors.toList()));
        System.out.println(rr);
        return rr;
    }
}
