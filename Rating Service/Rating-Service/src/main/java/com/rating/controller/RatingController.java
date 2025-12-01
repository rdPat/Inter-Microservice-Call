package com.rating.controller;

import com.rating.entity.Rating;
import com.rating.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    // CREATE
    @PostMapping
    public Rating createRating(@RequestBody Rating rating) {
        return ratingService.createRating(rating);
    }

    // GET ALL
    @GetMapping
    public List<Rating> getAll() {
        return ratingService.getAllRatings();
    }

    // GET RATING BY RATING-ID
    @GetMapping("/{ratingId}")
    public Rating getRating(@PathVariable String ratingId) {
        return ratingService.getByRatingId(ratingId);
    }

    // GET RATINGS OF A USER
    @GetMapping("/users/{userId}")
    public List<Rating> getByUserId(@PathVariable String userId) {
        return ratingService.getRatingsByUserId(userId);
    }

    // GET RATINGS OF A HOTEL
    @GetMapping("/hotels/{hotelId}")
    public List<Rating> getByHotelId(@PathVariable String hotelId) {
        return ratingService.getRatingsByHotelId(hotelId);
    }

    // DELETE
    @DeleteMapping("/{ratingId}")
    public String deleteRating(@PathVariable String ratingId) {
        return ratingService.deleteRating(ratingId);
    }
}