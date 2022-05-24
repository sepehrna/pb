package com.pb.coreservices.controller.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CouponDto extends DataTransferObject {

    private Long id;
    private String name;

    private Set<CouponLicenseDto> couponLicenseSet;

}
