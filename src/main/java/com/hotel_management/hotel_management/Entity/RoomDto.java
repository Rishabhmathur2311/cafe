package com.hotel_management.hotel_management.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class RoomDto {

    private Integer id;

    private String name;

    private String type;

    private Long price;

    private boolean available;
}
