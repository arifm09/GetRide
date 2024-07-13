package com.example.GetRide.dto.response;

import com.example.GetRide.Enum.BookingStatus;
import com.example.GetRide.dto.request.CustomerResponse;
import com.example.GetRide.model.Driver;
import com.example.GetRide.repository.DriverRepository;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingResponse {
    String pickUp;
    String destination;
    double totalDistance;

    double totalFare;

    CustomerResponse customerResponse;

    DriverResponse driverResponse;
    BookingStatus bookingStatus;
}
