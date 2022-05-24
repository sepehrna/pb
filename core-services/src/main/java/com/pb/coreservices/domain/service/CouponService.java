package com.pb.coreservices.domain.service;


import com.pb.coreservices.domain.entity.Coupon;
import com.pb.coreservices.domain.entity.Member;

public interface CouponService {

    Coupon addCoupon(Coupon coupon);

    Boolean assignCouponToMember(Member member, Coupon coupon);

}
