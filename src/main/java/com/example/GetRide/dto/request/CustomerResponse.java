package com.example.GetRide.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerResponse {
    private String name;
    private String emailId;
    private int age;
}
