package com.rating.controller;

import com.rating.entity.Rating;
import com.rating.service.RatingService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTML;
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
    int retryCount=1;

    @GetMapping("/users/{userId}")
    @CircuitBreaker(name = "ratingHotelCircuitBreaker",fallbackMethod = "ratingHotelFallbackMethod")
    //@Retry(name="ratingHotelRetry", fallbackMethod = "ratingHotelFallbackMethod")
    public List<Rating> getByUserId(@PathVariable Long userId) {
        System.out.println("------------>retry count "+retryCount++);
        return ratingService.getRatingsByUserId(userId);

    }

    //fallback method if hotel service is down
    public List<Rating> ratingHotelFallbackMethod(Long userId, Throwable ex) {

        Rating dummy = Rating.builder()
                .ratingId("dummyID")
                .id(0L)
                .feedback("Hotel-Service is down — fallback response")
                .build();

        return List.of(dummy);
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