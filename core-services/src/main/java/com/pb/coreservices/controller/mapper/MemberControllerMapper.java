package com.pb.coreservices.controller.mapper;

import com.pb.coreservices.controller.dto.MemberDto;
import com.pb.coreservices.domain.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring"
        , uses = {MemberCouponControllerMapper.class}
        , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MemberControllerMapper {

    Member map(MemberDto memberDto);

    MemberDto map(Member member);

}
