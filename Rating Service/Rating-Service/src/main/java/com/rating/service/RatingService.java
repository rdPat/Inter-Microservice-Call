package com.rating.service;

import com.rating.entity.Hotel;
import com.rating.entity.Rating;
import com.rating.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.uid;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    private final RestTemplate restTemplate;

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

    public List<Rating> getRatingsByUserId(Long userId) {

        List<Rating> response=ratingRepository.findByUserId(userId);
        List<Hotel> h=new ArrayList<>();
        System.out.println("#########"+response);
        for(Rating r:response)
        {
            String hid=r.getHotelId();
            Hotel hotel = restTemplate.getForObject(
                    "http://Hotel-Service/hotels/" + hid,
                    Hotel.class
            );
            r.setHotel(hotel);
        }

        return response;

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
