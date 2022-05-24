package com.pb.coreservices.domain.service;

import com.pb.coreservices.domain.entity.Coupon;
import com.pb.coreservices.domain.exception.DomainException;
import com.pb.coreservices.domain.exception.MandatoryFieldEmptyException;
import com.pb.coreservices.util.DateTimeUtil;

import java.util.function.Function;
import java.util.function.Predicate;

import static com.pb.coreservices.domain.service.ValidatorResult.invalid;
import static com.pb.coreservices.domain.service.ValidatorResult.valid;


/**
 * This is a validator class which designed with the combinator design pattern for validating the business's rules
 * that related to the member entity
 */
public interface CouponValidator extends Function<Coupon, ValidatorResult> {

    static CouponValidator isNameNullOrEmpty() {
        return holds(coupon -> coupon.getName() != null && !coupon.getName().trim().isEmpty(),
                new MandatoryFieldEmptyException("Coupon", "name"));
    }

    static CouponValidator hasValidLicense() {
        return holds(coupon ->
                        coupon.getCouponLicenseSet() != null
                                && coupon.getCouponLicenseSet()
                                .stream()
                                .anyMatch(couponLicense ->
                                        couponLicense.getId() != null || (couponLicense.getValidFrom().before(couponLicense.getValidUtil())
                                                && (couponLicense.getValidUtil().after(DateTimeUtil.getCurrentTime())))),
                new MandatoryFieldEmptyException("Coupon", "name"));
    }

    static CouponValidator holds(Predicate<Coupon> p, DomainException domainException) {
        return coupon -> p.test(coupon) ? valid() : invalid(domainException);
    }

    default CouponValidator and(CouponValidator other) {
        return coupon -> {
            ValidatorResult result = this.apply(coupon);
            return result.isValid() ? other.apply(coupon) : result;
        };
    }
}
