package com.example.GetRide.repository;

import com.example.GetRide.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {

    @Query(value = "select * from booking where driver_id= :id and booking_status='CONFIRMED'",nativeQuery = true)
    Booking getBooking(int id);
}
