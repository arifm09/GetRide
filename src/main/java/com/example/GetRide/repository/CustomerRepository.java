package com.example.GetRide.repository;

import com.example.GetRide.Enum.Gender;
import com.example.GetRide.model.Customer;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByEmailId(String emailId);

    List<Customer> findByName(String name);

//    @Query(value = "select * from customer where gender= :gender and age>= :age",nativeQuery = true)
//  List<Customer>  findBySpecificGenderAndAge(Gender gender, int age);

    @Query(value = "select c from Customer c where c.gender= :gender and c.age>= :age",nativeQuery = false)
    List<Customer>  getAllBySpecificGenderAndGreaterThanAge(Gender gender, int age);


}
