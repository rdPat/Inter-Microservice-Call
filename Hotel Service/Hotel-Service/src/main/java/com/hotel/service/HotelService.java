package com.hotel.service;

import com.hotel.entity.Hotel;
import com.hotel.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    private String generateHotelId() {
        Long count = hotelRepository.count() + 1;
        return "H" + String.format("%03d", count); // H001, H002, H003...
    }

    public Hotel createHotel(Hotel hotel) {
        hotel.setHotelId(generateHotelId());
        return hotelRepository.save(hotel);
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelByHotelId(String hotelId) {
        Hotel h=hotelRepository.findByHotelId(hotelId);
        System.out.println("###########"+h);
        return h;
    }



    public Hotel updateHotel(String hotelId, Hotel hotel) {
        Hotel existing = hotelRepository.findByHotelId(hotelId);
        if(existing == null) return null;

        existing.setName(hotel.getName());
        existing.setLocation(hotel.getLocation());
        existing.setAbout(hotel.getAbout());

        return hotelRepository.save(existing);
    }

    public String deleteHotel(String hotelId) {
        Hotel existing = hotelRepository.findByHotelId(hotelId);
        if(existing != null) hotelRepository.delete(existing);

        return "Hotel deleted";
    }
}