package com.pb.coreservices.controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MemberCouponDto extends DataTransferObject {

    private Long id;
    private CouponDto coupon;

}
