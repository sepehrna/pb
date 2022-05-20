package com.pb.coreservices.controller.mapper;

import com.pb.coreservices.controller.dto.ApplicationUserDetailDto;
import com.pb.coreservices.domain.entity.ApplicationUserDetail;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface ApplicationUserDetailControllerMapper {

    ApplicationUserDetailDto map(ApplicationUserDetail applicationUserDetail);

}
