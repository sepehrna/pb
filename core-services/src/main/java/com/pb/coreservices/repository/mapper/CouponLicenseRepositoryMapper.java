package com.pb.coreservices.repository.mapper;

import com.pb.coreservices.domain.entity.CouponLicense;
import com.pb.coreservices.repository.dao.CouponLicenseDao;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "Spring"
        , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class CouponLicenseRepositoryMapper {

    public abstract CouponLicenseDao map(CouponLicense couponLicense);

}
