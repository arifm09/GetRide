package com.example.GetRide.repository;

import com.example.GetRide.model.Driver;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DriverRepository extends JpaRepository<Driver,Integer> {
    Driver getDriverByMobNo(Long mobNo);

    @Modifying
    @Transactional
    @Query(value = "UPDATE driver d set d.mob_no= :newMobNo WHERE d.mob_no= :mobNo",nativeQuery = true)
    void updateDriverMobNo(Long mobNo, Long newMobNo);

}
