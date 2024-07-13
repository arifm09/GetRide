package com.example.GetRide.dto.request;

import com.example.GetRide.model.Cab;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverRequest {
    String name;

    int age;

    String drivingLicence;

    Long mobNo;

    CabRequest cabRequest;
}
