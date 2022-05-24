package com.pb.coreservices.domain.entity;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Member extends Entity {

    private Long id;
    private String name;
    private String lastName;

    private Set<MemberCoupon> memberCouponSet;

}
