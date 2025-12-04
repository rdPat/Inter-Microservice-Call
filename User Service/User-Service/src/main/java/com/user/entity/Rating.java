package com.user.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    //this class is injected manually into user service to use in response of GET api
    private String ratingId;   // UUID
    private Long userId;
    private String hotelId;
    private int rating;
    private String feedback;

    private List<Hotel> hotel;
}