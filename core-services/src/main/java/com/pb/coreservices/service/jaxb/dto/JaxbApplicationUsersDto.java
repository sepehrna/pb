package com.pb.coreservices.service.jaxb.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@XmlRootElement(name = "application-users")
public class JaxbApplicationUsersDto {

    private Set<JaxbApplicationUserDetailDto> applicationUserDetails;

    @XmlElement(name = "user")
    public void setApplicationUserDetails(Set<JaxbApplicationUserDetailDto> applicationUserDetails) {
        this.applicationUserDetails = applicationUserDetails;
    }
}
