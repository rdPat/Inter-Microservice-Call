package com.hotel.controller;

import com.hotel.entity.Hotel;
import com.hotel.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    // CREATE
    @PostMapping
    public Hotel createHotel(@RequestBody Hotel hotel) {
        return hotelService.createHotel(hotel);
    }

    // GET ALL
    @GetMapping
    public List<Hotel> getAll() {
        return hotelService.getAllHotels();
    }

    // GET BY HOTEL ID
    @GetMapping("/{hotelId}")
    public Hotel getHotel(@PathVariable String hotelId) {
        return hotelService.getHotelByHotelId(hotelId);
    }



    // UPDATE
    @PutMapping("/{hotelId}")
    public Hotel updateHotel(@PathVariable String hotelId, @RequestBody Hotel hotel) {
        return hotelService.updateHotel(hotelId, hotel);
    }

    // DELETE
    @DeleteMapping("/{hotelId}")
    public String deleteHotel(@PathVariable String hotelId) {
        return hotelService.deleteHotel(hotelId);
    }
}