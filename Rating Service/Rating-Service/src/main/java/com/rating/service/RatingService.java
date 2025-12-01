package com.rating.service;

import com.rating.entity.Rating;
import com.rating.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;

    public Rating createRating(Rating rating) {
        rating.setRatingId(UUID.randomUUID().toString());
        return ratingRepository.save(rating);
    }

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    public Rating getByRatingId(String ratingId) {
        return ratingRepository.findByRatingId(ratingId);
    }

    public List<Rating> getRatingsByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    public List<Rating> getRatingsByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }

    public String deleteRating(String ratingId) {
        Rating rating = ratingRepository.findByRatingId(ratingId);
        if (rating != null) ratingRepository.delete(rating);
        return "Rating deleted";
    }
}
