package com.pb.coreservices.repository.mapper;

import com.pb.coreservices.domain.entity.Coupon;
import com.pb.coreservices.repository.CouponRepository;
import com.pb.coreservices.repository.dao.CouponDao;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Mapper(componentModel = "spring"
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

    public abstract CouponDao map(Coupon coupon);

    public abstract Coupon map(CouponDao coupon);

}
