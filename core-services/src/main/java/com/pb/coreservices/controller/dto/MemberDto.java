package com.pb.coreservices.controller.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MemberDto extends DataTransferObject {

    private Long id;
    private String name;
    private String lastName;

    private Set<MemberCouponDto> memberCouponSet;

}
