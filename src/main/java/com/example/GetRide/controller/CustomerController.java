package com.example.GetRide.controller;

import com.example.GetRide.Enum.Gender;
import com.example.GetRide.dto.request.CustomerRequest;
import com.example.GetRide.dto.request.CustomerResponse;
import com.example.GetRide.dto.response.BookingResponse;
import com.example.GetRide.model.Booking;
import com.example.GetRide.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {


    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity addCustomer(@RequestBody CustomerRequest customerRequest){
        CustomerResponse  customerResponse = customerService.addCustomer(customerRequest);
        return new ResponseEntity(customerResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getCustomer(@RequestParam("emailId") String emailId){
        CustomerResponse customerResponse =  customerService.getCustomer(emailId);
        return new ResponseEntity(customerResponse, HttpStatus.ACCEPTED);
    }

    @GetMapping("/gender/{gender}/age/{age}")
    public ResponseEntity getCustomer(@PathVariable("gender") Gender gender, @PathVariable("age") int age){
        List<CustomerResponse> customerResponses = customerService.getCustomer(gender,age);
        return new ResponseEntity(customerResponses, HttpStatus.ACCEPTED);
    }

    @GetMapping("/bookings/{emailId}")
    public ResponseEntity getListOfBookingsOfCustomer(@PathVariable("emailId") String emailId){
       List<BookingResponse> bookingResponses =  customerService.getListOfBookingsOfCustomer(emailId);
        return new ResponseEntity(bookingResponses,HttpStatus.ACCEPTED);
    }

    @PutMapping("/ride-cancel/{emailId}/{mobNo}")
    public ResponseEntity cancelRide(@PathVariable("emailId") String emailId, @PathVariable("mobNo") Long mobNo){
        String response = customerService.cancelRide(emailId,mobNo);
        return new ResponseEntity(response,HttpStatus.ACCEPTED);
    }
}
