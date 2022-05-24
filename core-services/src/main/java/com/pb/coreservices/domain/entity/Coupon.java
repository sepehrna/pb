package com.pb.coreservices.domain.entity;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Coupon extends Entity {

    private Long id;
    private String name;

    private Set<CouponLicense> couponLicenseSet;

}
