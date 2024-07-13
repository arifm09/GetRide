package com.example.GetRide.controller;

import com.example.GetRide.dto.request.BookingRequest;
import com.example.GetRide.dto.response.BookingResponse;
import com.example.GetRide.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/book")
@RequiredArgsConstructor
public class BookingController {

    public final BookingService bookingService;

    @PostMapping
    public ResponseEntity addBooking(@RequestBody BookingRequest bookingRequest){

        try {
          BookingResponse response = bookingService.addBooking(bookingRequest);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/completed")
    public ResponseEntity rideCompleted(@RequestParam("mobNo") Long mobNo){
        bookingService.rideCompleted(mobNo);
        return new ResponseEntity("Ride Completed",HttpStatus.ACCEPTED);
    }

}
