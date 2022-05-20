package com.pb.coreservices.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUsers {

    private Set<ApplicationUserDetail> applicationUserDetailDtoSet;

}
