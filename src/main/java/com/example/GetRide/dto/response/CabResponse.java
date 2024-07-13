package com.example.GetRide.dto.response;

import com.example.GetRide.Enum.CabType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CabResponse {
    String cabNumber;
    CabType cabType;
    double farePerKm;

}
