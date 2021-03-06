package com.pb.coreservices.service.jaxb.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@XmlRootElement
@XmlType(propOrder = {"username", "password", "role" })
public class JaxbApplicationUserDetailDto {

    private String username;
    private String password;
    private String role;

    @XmlAttribute
    public void setUsername(String username) {
        this.username = username;
    }

    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }

    @XmlElement
    public void setRole(String role) {
        this.role = role;
    }
}
