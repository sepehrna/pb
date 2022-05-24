package com.pb.coreservices.repository.mapper;

import com.pb.coreservices.domain.entity.MemberCoupon;
import com.pb.coreservices.repository.MemberCouponRepository;
import com.pb.coreservices.repository.dao.MemberCouponDao;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Mapper(componentModel = "spring"
        , uses = {CouponRepositoryMapper.class}
        , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class MemberCouponRepositoryMapper {

    private MemberCouponRepository memberCouponRepository;

    @Autowired
    public void setMemberCouponRepository(MemberCouponRepository memberCouponRepository) {
        this.memberCouponRepository = memberCouponRepository;
    }

    @BeforeMapping
    void loadDao(@MappingTarget MemberCouponDao memberCouponDao, MemberCoupon memberCoupon) {
        if (memberCoupon.getId() != null) {
            Optional<MemberCouponDao> optional = memberCouponRepository.findById(memberCoupon.getId());
            memberCouponDao = optional.orElse(memberCouponDao);
        }
    }

    abstract MemberCouponDao map(MemberCoupon memberCoupon);
}
