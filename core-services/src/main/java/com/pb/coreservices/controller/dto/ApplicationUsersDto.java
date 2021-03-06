package com.pb.coreservices.controller.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ApplicationUsersDto extends DataTransferObject {

    private Set<ApplicationUserDetailDto> applicationUserDetails;

}
