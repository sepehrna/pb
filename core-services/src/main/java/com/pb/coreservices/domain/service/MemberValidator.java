package com.pb.coreservices.domain.service;

import com.pb.coreservices.domain.entity.Member;
import com.pb.coreservices.domain.exception.DomainException;
import com.pb.coreservices.domain.exception.MandatoryFieldEmptyException;

import java.util.function.Function;
import java.util.function.Predicate;

import static com.pb.coreservices.domain.service.ValidatorResult.invalid;
import static com.pb.coreservices.domain.service.ValidatorResult.valid;


/**
 * This is a validator class which designed with the combinator design pattern for validating the business's rules
 * that related to the member entity
 */
public interface MemberValidator extends Function<Member, ValidatorResult> {


    static MemberValidator isNameNullOrEmpty() {
        return holds(member -> member != null && !member.getName().trim().isEmpty(),
                new MandatoryFieldEmptyException("Member", "LastName"));
    }

    static MemberValidator isLastNameNullOrEmpty() {
        return holds(member -> member != null && !member.getLastName().trim().isEmpty(),
                new MandatoryFieldEmptyException("Member", "LastName"));
    }

    static MemberValidator holds(Predicate<Member> p, DomainException domainException) {
        return member -> p.test(member) ? valid() : invalid(domainException);
    }

    default MemberValidator and(MemberValidator other) {
        return member -> {
            final ValidatorResult result = this.apply(member);
            return result.isValid() ? other.apply(member) : result;
        };
    }
}
