package com.rating.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Hotel {

    private String hotelId;  // Unique ID for microservices communication
    private String name;
    private String location;
    private String about;
}
