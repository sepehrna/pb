package com.pb.coreservices.service.jaxb.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@Getter
@NoArgsConstructor
@XmlRootElement(name = "application-users")
public class JaxbApplicationUsersDto {

    private Set<JaxbApplicationUserDetailDto> applicationUserDetailDtoSet;

    @XmlElement(name = "user")
    public void setApplicationUserDetailDtoSet(Set<JaxbApplicationUserDetailDto> jaxbApplicationUserDetailDtoSet) {
        this.applicationUserDetailDtoSet = jaxbApplicationUserDetailDtoSet;
    }
}
