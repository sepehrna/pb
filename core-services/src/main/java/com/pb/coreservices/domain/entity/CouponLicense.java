package com.pb.coreservices.domain.entity;

import lombok.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CouponLicense extends Entity{

    private Long id;
    private Timestamp validFrom;
    private Timestamp validUtil;

}

