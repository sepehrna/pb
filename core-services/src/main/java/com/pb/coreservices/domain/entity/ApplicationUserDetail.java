package com.pb.coreservices.domain.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ApplicationUserDetail extends Entity {

    private String username;
    private String password;
    private String role;

}
