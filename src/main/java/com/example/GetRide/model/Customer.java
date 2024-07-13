package com.example.GetRide.model;

import com.example.GetRide.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name ="customer")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name")
    String name;

    int age;

    @Enumerated(value = EnumType.STRING)
    Gender gender;

    @Column(unique = true, nullable = false)
    String emailId;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<Booking> bookings = new ArrayList<>();
}
