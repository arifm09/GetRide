package com.example.GetRide.dto.request;

import com.example.GetRide.Enum.CabType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CabRequest {

    CabType cabType;

    String cabNumber;

    double farePerKm;

}
