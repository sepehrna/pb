package com.pb.coreservices.controller.mapper;

import com.pb.coreservices.controller.dto.CouponLicenseDto;
import com.pb.coreservices.domain.entity.CouponLicense;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring"
        , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CouponLicenseControllerMapper {

    CouponLicense map(CouponLicenseDto couponLicenseDto);

}
