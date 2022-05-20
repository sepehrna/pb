package com.pb.coreservices.service.jaxb.mapper;

import com.pb.coreservices.domain.entity.ApplicationUsers;
import com.pb.coreservices.service.jaxb.dto.JaxbApplicationUserDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
@Service
public interface JaxbApplicationUserDetailMapper {

    JaxbApplicationUserDetailDto map(ApplicationUsers applicationUsers);

}
