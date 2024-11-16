package com.hotel_management.hotel_management.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String type;

    private Long price;

    private boolean available;

    public RoomDto getRoom() {
        RoomDto room = new RoomDto();

        room.setId(id);
        room.setName(name);
        room.setType(type);
        room.setPrice(price);
        room.setAvailable(available);
        return room;
    }
}
