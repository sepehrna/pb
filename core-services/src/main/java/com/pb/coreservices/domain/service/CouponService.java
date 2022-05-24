package com.pb.coreservices.domain.service;


import com.pb.coreservices.domain.entity.Coupon;
import com.pb.coreservices.domain.entity.Member;

import java.util.Set;

public interface CouponService {

    Coupon addCoupon(Coupon coupon);

    Member assignCouponToMember(String memberId, String couponId);

    Set<Coupon> findMemberCoupon(String memberId);
}
