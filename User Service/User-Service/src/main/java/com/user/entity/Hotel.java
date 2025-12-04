package com.user.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Hotel {

    //this class is injected manually into user service to use in response of GET api
    private String hotelId;  // Unique ID for microservices communication
    private String name;
    private String location;
    private String about;
}