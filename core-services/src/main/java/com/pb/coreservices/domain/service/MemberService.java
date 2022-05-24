package com.pb.coreservices.domain.service;

import com.pb.coreservices.domain.entity.Member;
import com.pb.coreservices.domain.exception.DomainException;

/**
 * Domain service definition of member entity
 */
public interface MemberService {

    Member addMember(Member member) throws DomainException;

}
