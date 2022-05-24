package com.pb.coreservices.controller.mapper;

import com.pb.coreservices.controller.dto.MemberCouponDto;
import com.pb.coreservices.domain.entity.MemberCoupon;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring"
        , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MemberCouponControllerMapper {

    @InheritInverseConfiguration
    MemberCoupon map(MemberCouponDto memberCouponDto);

}
