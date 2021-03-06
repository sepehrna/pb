package com.pb.coreservices.domain.entity;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ApplicationUsers extends Entity{

    private Set<ApplicationUserDetail> applicationUserDetails;

}
