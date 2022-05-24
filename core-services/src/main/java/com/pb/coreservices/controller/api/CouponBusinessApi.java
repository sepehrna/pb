package com.pb.coreservices.controller.api;

import com.pb.coreservices.controller.dto.CouponDto;
import com.pb.coreservices.controller.dto.MemberDto;
import com.pb.coreservices.controller.handler.ApiResultHandler;
import com.pb.coreservices.controller.handler.ApiResultHandlerImpl;
import com.pb.coreservices.controller.mapper.CouponControllerMapper;
import com.pb.coreservices.controller.mapper.MemberControllerMapper;
import com.pb.coreservices.domain.entity.Coupon;
import com.pb.coreservices.domain.entity.Member;
import com.pb.coreservices.domain.service.CouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/coupon")
public class CouponBusinessApi {

    private final Logger logger = LoggerFactory.getLogger(CouponBusinessApi.class);

    private final CouponService couponService;
    private final CouponControllerMapper couponControllerMapper;
    private final MemberControllerMapper memberControllerMapper;

    @Autowired
    public CouponBusinessApi(CouponService couponService, CouponControllerMapper couponControllerMapper, MemberControllerMapper memberControllerMapper) {
        this.couponService = couponService;
        this.couponControllerMapper = couponControllerMapper;
        this.memberControllerMapper = memberControllerMapper;
    }

    @PostMapping("/add-coupon")
    public ResponseEntity<CouponDto> addCoupon(@RequestBody CouponDto couponDto) {
        logger.info("Add coupon api has been called");
        ApiResultHandler<CouponDto> apiResultHandler = new ApiResultHandlerImpl<>();
        ResponseEntity<CouponDto> response;
        try {
    //            CouponDto couponDto = CouponDto.
    //                    builder().
    //                    name(couponName).
    //                    build();
            Coupon coupon = couponControllerMapper.map(couponDto);
            Coupon savedCoupon = couponService.addCoupon(coupon);
            CouponDto mappedSavedCoupon = couponControllerMapper.map(savedCoupon);
            response = apiResultHandler.handle(mappedSavedCoupon);
        } catch (Exception e) {
            logger.error("Add coupon api call been done with error");
            e.printStackTrace();
            response = apiResultHandler.handle(e);
        }
        return response;
    }

    @PatchMapping("/assign-coupon")
    public ResponseEntity<MemberDto> assignCoupon(@RequestParam String memberId, @RequestParam String couponId) {

        String introLogInfo = String.format("Assigning coupon with id %s to member with id %s", memberId, couponId);
        logger.info(introLogInfo);

        ApiResultHandler<MemberDto> apiResultHandler = new ApiResultHandlerImpl<>();
        ResponseEntity<MemberDto> response;
        try {
            Member savedMember = couponService.assignCouponToMember(memberId, couponId);
            MemberDto mappedMemberDto = memberControllerMapper.map(savedMember);
            response = apiResultHandler.handle(mappedMemberDto);
        } catch (Exception e) {
            logger.error(String.format("Assigning coupon with id %s to member with id %s", memberId, couponId));
            e.printStackTrace();
            response = apiResultHandler.handle(e);
        }
        return response;
    }

    @GetMapping("/get-member-coupon")
    public ResponseEntity<Set<CouponDto>> getMemberCoupon(@RequestParam String memberId) {

        logger.info(String.format("Getting coupons for member with id %s", memberId));

        ApiResultHandler<Set<CouponDto>> apiResultHandler = new ApiResultHandlerImpl<>();
        ResponseEntity<Set<CouponDto>> response;
        try {
            Set<Coupon> memberCoupon = couponService.findMemberCoupon(memberId);
            Set<CouponDto> couponDtoSet = couponControllerMapper.map(memberCoupon);
            response = apiResultHandler.handle(couponDtoSet);
        } catch (Exception e) {
            logger.info(String.format("Getting coupons service for member with id %s has been done with error", memberId));
            e.printStackTrace();
            response = apiResultHandler.handle(e);
        }
        return response;
    }

}
