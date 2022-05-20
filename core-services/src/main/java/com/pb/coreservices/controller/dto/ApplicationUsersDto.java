package com.pb.coreservices.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUsersDto {

    private Set<ApplicationUserDetailDto> applicationUserDetailDtoSet;

}
