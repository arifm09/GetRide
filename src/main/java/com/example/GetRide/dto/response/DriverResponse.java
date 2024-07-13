package com.example.GetRide.dto.response;

import com.example.GetRide.dto.request.CabRequest;
import com.example.GetRide.model.Driver;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverResponse {
    String name;
    int age;
    CabResponse cabResponse;
}
