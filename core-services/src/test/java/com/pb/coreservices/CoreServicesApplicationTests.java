package com.pb.coreservices;

import com.pb.coreservices.domain.entity.Coupon;
import com.pb.coreservices.domain.entity.CouponLicense;
import com.pb.coreservices.domain.entity.Member;
import com.pb.coreservices.domain.service.CouponService;
import com.pb.coreservices.domain.service.MemberService;
import com.pb.coreservices.util.DateTimeUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CoreServicesApplicationTests {

    private final MemberService memberService;
    private final CouponService couponService;

    private static String couponId;
    private static String memberId;

    @Autowired
    public CoreServicesApplicationTests(MemberService memberService, CouponService couponService) {
        this.memberService = memberService;
        this.couponService = couponService;
    }

    public static int getRandomNumber() {
        Random randomNumber = new Random();
        return randomNumber.nextInt(10000);
    }

    @Test
    void contextLoads() {
    }

    @Test
    @Order(1)
    void addMemberTestDefaultDatasource() {
        Member member = new Member();
        String memberRandomConcatenationValue = String.valueOf(getRandomNumber());
        member.setName("Name-Test-".concat(memberRandomConcatenationValue));
        member.setLastName("LastName-Test-".concat(memberRandomConcatenationValue));
        Member savedMember = memberService.addMember(member);
        Assertions.assertNotNull(savedMember);
        Assertions.assertNotNull(savedMember.getId());
        Assertions.assertNotEquals(savedMember.getId(), 0);
        System.out.println(savedMember.getId());
        memberId = String.valueOf(savedMember.getId());
    }

    @Test
    @Order(2)
    void addCouponTestDefaultDatasource() {

        Coupon coupon = new Coupon();
        String couponRandomConcatenationValue = String.valueOf(getRandomNumber());
        coupon.setName("Coupon-Test-".concat(couponRandomConcatenationValue));

        CouponLicense firstCouponLicense = new CouponLicense();
        Timestamp yesterday = new Timestamp(DateTimeUtil.getCurrentTime().getTime() - (24 * 3600 * 1000));
        Timestamp tomorrow = new Timestamp(DateTimeUtil.getCurrentTime().getTime() + (24 * 3600 * 1000));
        firstCouponLicense.setValidFrom(yesterday);
        firstCouponLicense.setValidUtil(tomorrow);

        CouponLicense secondCouponLicense = new CouponLicense();
        Timestamp theDayBeforeYesterday = new Timestamp(DateTimeUtil.getCurrentTime().getTime() - 2 * (24 * 3600 * 1000));
        Timestamp theDayAfterTomorrow = new Timestamp(DateTimeUtil.getCurrentTime().getTime() + 2 * (24 * 3600 * 1000));
        secondCouponLicense.setValidFrom(theDayBeforeYesterday);
        secondCouponLicense.setValidUtil(theDayAfterTomorrow);

        Set<CouponLicense> couponLicenses = new HashSet<>();
        couponLicenses.add(firstCouponLicense);
        couponLicenses.add(secondCouponLicense);

        coupon.setCouponLicenseSet(couponLicenses);

        Coupon savedCoupon = couponService.addCoupon(coupon);
        Assertions.assertNotNull(savedCoupon);
        Assertions.assertNotNull(savedCoupon.getId());
        Assertions.assertNotEquals(savedCoupon.getId(), 0);
        System.out.println(savedCoupon.getId());
        couponId = String.valueOf(savedCoupon.getId());

    }

    @Test
    @Order(3)
    void assignCouponToMemberDefaultDatasource() {
        Member member = couponService.assignCouponToMember(memberId, couponId);
        Assertions.assertNotNull(member.getMemberCouponSet());
        member
                .getMemberCouponSet()
                .forEach(memberCoupon -> Assertions
                        .assertNotNull(memberCoupon.getCoupon()));
    }


}
