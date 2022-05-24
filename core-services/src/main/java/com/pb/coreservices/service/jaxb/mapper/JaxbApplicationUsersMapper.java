package com.pb.coreservices.service.jaxb.mapper;

import com.pb.coreservices.domain.entity.ApplicationUsers;
import com.pb.coreservices.service.jaxb.dto.JaxbApplicationUsersDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring"
        , uses = {JaxbApplicationUserDetailMapper.class}
        , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Service
public interface JaxbApplicationUsersMapper {

    ApplicationUsers map(JaxbApplicationUsersDto jaxbApplicationUsersDto);

}
