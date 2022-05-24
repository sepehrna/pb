package com.pb.coreservices.service;

import com.pb.coreservices.domain.entity.Member;
import com.pb.coreservices.domain.exception.MandatoryFieldEmptyException;
import com.pb.coreservices.domain.service.MemberService;
import com.pb.coreservices.domain.service.MemberValidator;
import com.pb.coreservices.repository.MemberRepository;
import com.pb.coreservices.repository.dao.MemberDao;
import com.pb.coreservices.repository.mapper.MemberRepositoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    private final MemberRepository memberRepository;
    private final MemberRepositoryMapper memberRepositoryMapper;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, MemberRepositoryMapper memberRepositoryMapper) {
        this.memberRepository = memberRepository;
        this.memberRepositoryMapper = memberRepositoryMapper;
    }

    @Override
    public Member addMember(Member member) throws MandatoryFieldEmptyException {

        logger.info("Adding member process with method addMember has been started");

        MemberValidator
                .isNameNullOrEmpty()
                .and(MemberValidator.isLastNameNullOrEmpty())
                .apply(member)
                .getException()
                .ifPresent(domainException -> {
                    throw domainException;
                });

        MemberDao savedMember = memberRepository
                .save(memberRepositoryMapper.map(member));

        Member mappedSavedMember = memberRepositoryMapper.map(savedMember);

        logger.info("Adding member process with method addMember has been finished successfully");
        return mappedSavedMember;
    }

}
