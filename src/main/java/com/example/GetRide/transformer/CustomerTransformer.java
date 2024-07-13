package com.example.GetRide.transformer;

import com.example.GetRide.dto.request.CustomerRequest;
import com.example.GetRide.dto.request.CustomerResponse;
import com.example.GetRide.model.Customer;
import com.example.GetRide.repository.CustomerRepository;

public class CustomerTransformer {

    public static CustomerResponse customerToCustomerResponse(Customer customer){
        CustomerResponse customerResponse = CustomerResponse.builder()
                                            .name(customer.getName())
                                                    .age(customer.getAge())
                                            .emailId(customer.getEmailId())
                                            .build();
        return customerResponse;
    }

    public static Customer customerRequestToCustomer(CustomerRequest customerRequest){
        Customer customer = Customer.builder()
                                    .name(customerRequest.getName())
                                    .age(customerRequest.getAge())
                                    .emailId(customerRequest.getEmailId())
                                    .gender(customerRequest.getGender())
                                    .build();

        return customer;
    }
}
