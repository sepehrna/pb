package com.pb.coreservices.controller.mapper;

import com.pb.coreservices.controller.dto.ApplicationUsersDto;
import com.pb.coreservices.domain.entity.ApplicationUsers;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring", uses = {ApplicationUserDetailControllerMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface ApplicationUsersControllerMapper {

    ApplicationUsersDto map(ApplicationUsers applicationUser);

}
