package com.pb.coreservices.controller.api;

import com.pb.coreservices.controller.dto.CouponDto;
import com.pb.coreservices.controller.handler.ApiResultHandler;
import com.pb.coreservices.controller.handler.ApiResultHandlerImpl;
import com.pb.coreservices.controller.mapper.CouponControllerMapper;
import com.pb.coreservices.domain.entity.Coupon;
import com.pb.coreservices.domain.service.CouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coupon")
public class CouponBusinessApi {

    private final Logger logger = LoggerFactory.getLogger(CouponBusinessApi.class);

    private final CouponService couponService;
    private final CouponControllerMapper couponControllerMapper;

    @Autowired
    public CouponBusinessApi(CouponService couponService, CouponControllerMapper couponControllerMapper) {
        this.couponService = couponService;
        this.couponControllerMapper = couponControllerMapper;
    }

    @PostMapping("/add-coupon")
    public ResponseEntity<CouponDto> addCoupon(@RequestBody CouponDto couponDto) {
        logger.info("Add coupon api has been called");
        ApiResultHandler<CouponDto> apiResultHandler = new ApiResultHandlerImpl<>();
        ResponseEntity<CouponDto> response;
        try {
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
}
