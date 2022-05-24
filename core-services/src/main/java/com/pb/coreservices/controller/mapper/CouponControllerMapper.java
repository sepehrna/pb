package com.pb.coreservices.controller.mapper;

import com.pb.coreservices.controller.dto.CouponDto;
import com.pb.coreservices.domain.entity.Coupon;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring"
        , uses = {CouponLicenseControllerMapper.class}
        , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CouponControllerMapper {

    Coupon map(CouponDto couponDto);

    CouponDto map(Coupon coupon);

}
