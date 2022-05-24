package com.pb.coreservices.repository.mapper;

import com.pb.coreservices.domain.entity.Member;
import com.pb.coreservices.repository.MemberRepository;
import com.pb.coreservices.repository.dao.MemberDao;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Mapper(componentModel = "spring"
        , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class MemberRepositoryMapper {

    private MemberRepository memberRepository;

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @BeforeMapping
    void map(@MappingTarget MemberDao memberDao, Member member) {
        if (member.getId() != null) {
            Optional<MemberDao> memberDaoOptional = memberRepository.findById(member.getId());
            memberDao = memberDaoOptional.orElse(memberDao);
        }
    }

    public abstract MemberDao map(Member member);

    public abstract Member map(MemberDao memberDao);

}
