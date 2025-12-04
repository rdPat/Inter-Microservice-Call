package com.rating.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ratingId;   // UUID
    private Long userId;
    private String hotelId;
    private int rating;
    private String feedback;

    @Transient
    private Hotel hotel;


}
