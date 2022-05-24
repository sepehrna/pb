package com.pb.coreservices.controller.mapper;

import com.pb.coreservices.controller.dto.ApplicationUserDetailDto;
import com.pb.coreservices.domain.entity.ApplicationUserDetail;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring"
        , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ApplicationUserDetailControllerMapper {

    ApplicationUserDetailDto map(ApplicationUserDetail applicationUserDetail);

}
