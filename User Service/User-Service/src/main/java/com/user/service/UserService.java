package com.user.service;

import com.user.config.HotelService;
import com.user.entity.Hotel;
import com.user.entity.Rating;
import com.user.entity.User;
import com.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.juli.logging.LogFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final RestTemplate restTemplate;

    @Autowired
    HotelService hotelService;


    private final UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserRating(Long id) {

            User response= userRepository.findById(id).orElse(null);
        //get ratings done by this user
        //http://localhost:8083/ratings/users/1

        /*// Step 2: Call Rating-Service using userId
        Rating[] ratings = restTemplate.getForObject(
                "http://localhost:8083/ratings/users/" + id,
                Rating[].class
        );

        // Step 3: Convert array to List
        List<Rating> ratingList = Arrays.asList(ratings);

        // Step 4: Set ratings inside user (Transient field)
        response.setRatings(ratingList);*/

        Long uid=id;

        //do im call and convert in ArrayList.class
        //restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + uid, ArrayList.class);

        response.setRatings(restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" +
                uid, ArrayList.class));
        Hotel output= hotelService.getHotel("H001");
        System.out.println("#########------->"+output.toString());
        return response;
    }

    public User updateUser(Long id, User user) {
        User existing = userRepository.findById(id).orElse(null);
        if(existing == null) return null;

        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        existing.setAge(user.getAge());

        return userRepository.save(existing);
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User deleted successfully";
    }
}
