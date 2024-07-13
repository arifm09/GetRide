package com.example.GetRide.service;

import com.example.GetRide.Enum.BookingStatus;
import com.example.GetRide.Enum.Gender;
import com.example.GetRide.dto.request.CustomerRequest;
import com.example.GetRide.dto.request.CustomerResponse;
import com.example.GetRide.dto.response.BookingResponse;
import com.example.GetRide.model.Booking;
import com.example.GetRide.model.Customer;
import com.example.GetRide.model.Driver;
import com.example.GetRide.repository.BookingRepository;
import com.example.GetRide.repository.CustomerRepository;
import com.example.GetRide.repository.DriverRepository;
import com.example.GetRide.transformer.BookingTransformer;
import com.example.GetRide.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    JavaMailSender mailSender;
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {

        Customer customer = CustomerTransformer.customerRequestToCustomer(customerRequest);
        CustomerResponse customerResponse = CustomerTransformer.customerToCustomerResponse(customer);
         customerRepository.save(customer);

       return customerResponse;
    }

    public CustomerResponse getCustomer(String emailId) {
        Customer customer = customerRepository.findByEmailId(emailId);
        CustomerResponse customerResponse = CustomerTransformer.customerToCustomerResponse(customer);
        return customerResponse;
    }

    public List<CustomerResponse> getCustomer(Gender gender, int age) {
        List<Customer> customers = customerRepository.getAllBySpecificGenderAndGreaterThanAge(gender,age);
        List<CustomerResponse> customerResponses = new ArrayList<>();

        for(Customer customer: customers){
            CustomerResponse customerResponse = CustomerTransformer.customerToCustomerResponse(customer);
            customerResponses.add(customerResponse);
        }
        return customerResponses;
    }

    public List<BookingResponse> getListOfBookingsOfCustomer(String emailId) {
        Customer customer = customerRepository.findByEmailId(emailId);

        List<BookingResponse> bookingResponses = new ArrayList<>();

        for(Booking booking1 : customer.getBookings()){
            BookingResponse bookingResponse = BookingTransformer.bookingToBookingResponse(booking1);
            bookingResponses.add(bookingResponse);
        }
        return bookingResponses;
    }

    public String cancelRide(String emailId,Long mobNo) {
        Customer customer = customerRepository.findByEmailId(emailId);
        Driver driver = driverRepository.getDriverByMobNo(mobNo);
        Booking booking = bookingRepository.getBooking(driver.getId());

        driver.getCab().setBooked(false);
        booking.setBookingStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);

        List<Booking> customerBookings = customer.getBookings();
        List<Booking> driverBookings = driver.getBookings();

        for(Booking booking1: customerBookings){
            if(booking.getId()==booking1.getId()){
                booking1.setBookingStatus(BookingStatus.CANCELLED);
                break;
            }
        }
        for(Booking booking1: driverBookings){
            if(booking.getId()==booking1.getId()){
                booking1.setBookingStatus(BookingStatus.CANCELLED);
                break;
            }
        }

        customerRepository.save(customer);
        driverRepository.save(driver);

        sendMail(customer.getEmailId());
        return "Ride Cancelled Successfully";
    }

    private void sendMail(String emailId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("academytrics@gmail.com");
        message.setTo("arifmohd20001@gmail.com");
        message.setSubject("CANCELLED");
        message.setText("Ride Cancelled Successfully" +"\n"
                         + "Book again have a nice day" +"\n\n"
                         + "Warm Regards"+"\n"
                         + "GetRide Team");


        mailSender.send(message);
    }
}
