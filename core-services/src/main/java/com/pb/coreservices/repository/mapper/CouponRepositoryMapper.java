package com.pb.coreservices.repository.mapper;

import com.pb.coreservices.domain.entity.Coupon;
import com.pb.coreservices.repository.CouponRepository;
import com.pb.coreservices.repository.dao.CouponDao;
import com.pb.coreservices.repository.dao.CouponLicenseDao;
import com.pb.coreservices.repository.dao.MemberCouponDao;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Mapper(componentModel = "spring"
        , uses = {CouponLicenseRepositoryMapper.class}
        , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class CouponRepositoryMapper {

    private CouponRepository couponRepository;

    @Autowired
    public void setCouponRepository(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @BeforeMapping
    void loadDao(@MappingTarget CouponDao couponDao, Coupon coupon) {
        if (coupon.getId() != null) {
            Optional<CouponDao> optionalCouponDao = couponRepository.findById(coupon.getId());
            couponDao = optionalCouponDao.orElse(couponDao);
        }
    }

    @AfterMapping
    void setObjects(@MappingTarget CouponDao couponDao) {
        for (CouponLicenseDao couponLicenseDao : couponDao.getCouponLicenseSet()) {
            couponLicenseDao.setCoupon(couponDao);
        }
    }

    public abstract CouponDao map(Coupon coupon);

    public abstract Coupon map(CouponDao coupon);

}
