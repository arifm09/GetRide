package com.example.GetRide.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingRequest {
    String pickUp;

    String destination;
    double totalDistance;
    String customerEmailId;
}
