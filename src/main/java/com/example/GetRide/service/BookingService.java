package com.example.GetRide.service;

import com.example.GetRide.Enum.BookingStatus;
import com.example.GetRide.dto.request.BookingRequest;
import com.example.GetRide.dto.response.BookingResponse;
import com.example.GetRide.exception.CabNotFoundException;
import com.example.GetRide.exception.CustomerNotFoundException;
import com.example.GetRide.model.Booking;
import com.example.GetRide.model.Cab;
import com.example.GetRide.model.Customer;
import com.example.GetRide.model.Driver;
import com.example.GetRide.repository.BookingRepository;
import com.example.GetRide.repository.CabRepository;
import com.example.GetRide.repository.CustomerRepository;
import com.example.GetRide.repository.DriverRepository;
import com.example.GetRide.transformer.BookingTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {


   public final CustomerRepository customerRepository;
   public final CabRepository cabRepository;
   public final BookingRepository bookingRepository;
   public final DriverRepository driverRepository;

   @Autowired
    JavaMailSender javaMailSender;

   public BookingResponse addBooking(BookingRequest bookingRequest) {

        Customer customer = customerRepository.findByEmailId(bookingRequest.getCustomerEmailId());

        if(ObjectUtils.isEmpty(customer)){
            throw new CustomerNotFoundException("Customer not found");
        }


        Optional<Cab> cabOptional = cabRepository.getRandomCab();
        Cab cab = cabOptional.get();

        if(ObjectUtils.isEmpty(cab)){
            throw new CabNotFoundException("Seems like all drivers are busy");
        }

        cab.setBooked(true);
        Driver driver = cab.getDriver();

        Booking booking = BookingTransformer.bookingRequestToBooking(bookingRequest,cab);
        booking.setCustomer(customer);
        booking.setDriver(driver);

        Booking savedBooking = bookingRepository.save(booking);

        customer.getBookings().add(savedBooking);
        driver.getBookings().add(savedBooking);

        customerRepository.save(customer);
        driverRepository.save(driver);

        // entity to response

        BookingResponse bookingResponse = BookingTransformer.bookingToBookingResponse(booking);

        sendEmail(savedBooking);
        return bookingResponse;


    }

    private void sendEmail(Booking booking) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("academytrics@gmail.com");
        simpleMailMessage.setTo(booking.getCustomer().getEmailId());
        simpleMailMessage.setSubject("Booking Congfirmed!!");
        simpleMailMessage.setText("Congrats "+booking.getCustomer().getName()+ " your ride is confirmed "
                                   + " your booking Id is "+ booking.getBookingId() +"\n"
                                   + "Pick Up - " + booking.getPickUp() +"\n"
                                   + "Destination - " + booking.getDestination() +"\n"
                                   + "Total Fare = " + booking.getTotalFare() +"\n\n"
                                   + "Best Regards, \n"
                                   + "GetRide Team");

        javaMailSender.send(simpleMailMessage);
    }

    public void rideCompleted(Long mobNo) {
         Driver driver = driverRepository.getDriverByMobNo(mobNo);
         Cab cab = driver.getCab();
         cab.setBooked(false);

         Booking booking = bookingRepository.getBooking(driver.getId());

        for(Booking booking1 : booking.getCustomer().getBookings()){
            if(booking1==booking){
                booking1.setBookingStatus(BookingStatus.COMPLETED);
                break;
            }
        }
        for(Booking booking1 : booking.getDriver().getBookings()){
            if(booking1==booking){
                booking1.setBookingStatus(BookingStatus.COMPLETED);
                break;
            }
        }

         booking.setBookingStatus(BookingStatus.COMPLETED);

         bookingRepository.save(booking);
         customerRepository.save(booking.getCustomer());
         driverRepository.save(driver);
    }

}
