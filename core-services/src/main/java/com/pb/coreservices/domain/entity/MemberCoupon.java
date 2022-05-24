package com.pb.coreservices.domain.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MemberCoupon extends Entity {

    private Long id;
    private Coupon coupon;

}
