package com.example.GetRide.transformer;

import com.example.GetRide.Enum.BookingStatus;
import com.example.GetRide.dto.request.BookingRequest;
import com.example.GetRide.dto.response.BookingResponse;
import com.example.GetRide.dto.response.DriverResponse;
import com.example.GetRide.model.Booking;
import com.example.GetRide.model.Cab;
import com.example.GetRide.model.Driver;
import com.example.GetRide.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class BookingTransformer {

    @Autowired
    CustomerRepository customerRepository;
    public static Booking bookingRequestToBooking(BookingRequest bookingRequest, Cab cab){
        return Booking.builder()
                .bookingId(String.valueOf(UUID.randomUUID()))
                .pickUp(bookingRequest.getPickUp())
                .destination(bookingRequest.getDestination())
                .totalDistance(bookingRequest.getTotalDistance())
                .totalFare(bookingRequest.getTotalDistance()*cab.getFarePerKm())
                .bookingStatus(BookingStatus.CONFIRMED)
                .build();
    }

    public static BookingResponse bookingToBookingResponse(Booking booking) {
        return BookingResponse.builder()
                .pickUp(booking.getPickUp())
                .destination(booking.getDestination())
                .totalDistance(booking.getTotalDistance())
                .totalFare(booking.getTotalFare())
                .bookingStatus(booking.getBookingStatus())
                .customerResponse(CustomerTransformer.customerToCustomerResponse(booking.getCustomer()))
                .driverResponse(DriverTransformer.driverToDriverResponse(booking.getDriver()))
                .build();
    }
}
