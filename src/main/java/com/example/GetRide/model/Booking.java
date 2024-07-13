package com.example.GetRide.model;

import com.example.GetRide.Enum.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String bookingId;

    String pickUp;

    String destination;

    @Enumerated(value = EnumType.STRING)
    BookingStatus bookingStatus;

    @CreationTimestamp
    Date dateOfBooked;

    double totalDistance;

    double totalFare;

    @ManyToOne
    @JoinColumn
    Customer customer;

    @ManyToOne
    @JoinColumn
    Driver driver;

}
