package com.pb.coreservices.service;

import com.pb.coreservices.domain.entity.Coupon;
import com.pb.coreservices.domain.entity.Member;
import com.pb.coreservices.domain.exception.MandatoryFieldEmptyException;
import com.pb.coreservices.domain.service.CouponService;
import com.pb.coreservices.domain.service.CouponValidator;
import com.pb.coreservices.repository.CouponRepository;
import com.pb.coreservices.repository.dao.CouponDao;
import com.pb.coreservices.repository.mapper.CouponRepositoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponServiceImpl implements CouponService {

    private final Logger logger = LoggerFactory.getLogger(CouponServiceImpl.class);

    private final CouponRepository couponRepository;
    private final CouponRepositoryMapper couponRepositoryMapper;

    @Autowired
    public CouponServiceImpl(CouponRepository couponRepository, CouponRepositoryMapper couponRepositoryMapper) {
        this.couponRepository = couponRepository;
        this.couponRepositoryMapper = couponRepositoryMapper;
    }

    @Override
    public Coupon addCoupon(Coupon coupon) throws MandatoryFieldEmptyException {

        logger.info("Add coupon service has been called");

        CouponValidator
                .isNameNullOrEmpty()
                .and(CouponValidator.isValidLicense())
                .apply(coupon)
                .getException()
                .ifPresent(domainException -> {
                    throw domainException;
                });

        CouponDao savedCoupon = couponRepository
                .save(couponRepositoryMapper.map(coupon));

        Coupon mappedSavedCoupon = couponRepositoryMapper.map(savedCoupon);

        logger.info("Add coupon service has been ended");
        return mappedSavedCoupon;
    }

    @Override
    public Boolean assignCouponToMember(Member member, Coupon coupon) {
        return null;
    }
}
