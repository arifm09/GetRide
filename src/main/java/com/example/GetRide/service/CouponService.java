package com.example.GetRide.service;

import com.example.GetRide.model.Coupon;
import com.example.GetRide.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponService {

    @Autowired
    CouponRepository couponRepository;
    public String addCoupon(String couponCode, int percentageDiscount) {
        Coupon coupen = Coupon.builder()
                                .couponCode(couponCode)
                                .percentageDiscount(percentageDiscount)
                                .build();;

        couponRepository.save(coupen);

        return "Coupon has been added successfully";
    }
}
