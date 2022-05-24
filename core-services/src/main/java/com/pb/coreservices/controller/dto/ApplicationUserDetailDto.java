package com.pb.coreservices.controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ApplicationUserDetailDto extends DataTransferObject {

    private String username;
    private String password;
    private String role;

}
