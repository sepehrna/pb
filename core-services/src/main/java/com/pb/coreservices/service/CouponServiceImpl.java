package com.pb.coreservices.service;

import com.pb.coreservices.domain.entity.Coupon;
import com.pb.coreservices.domain.entity.CouponLicense;
import com.pb.coreservices.domain.entity.Member;
import com.pb.coreservices.domain.entity.MemberCoupon;
import com.pb.coreservices.domain.exception.MandatoryFieldEmptyException;
import com.pb.coreservices.domain.service.CouponService;
import com.pb.coreservices.domain.service.CouponValidator;
import com.pb.coreservices.repository.CouponRepository;
import com.pb.coreservices.repository.MemberRepository;
import com.pb.coreservices.repository.dao.CouponDao;
import com.pb.coreservices.repository.dao.MemberCouponDao;
import com.pb.coreservices.repository.dao.MemberDao;
import com.pb.coreservices.repository.mapper.CouponRepositoryMapper;
import com.pb.coreservices.repository.mapper.MemberRepositoryMapper;
import com.pb.coreservices.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


@Service
public class CouponServiceImpl implements CouponService {

    private final Logger logger = LoggerFactory.getLogger(CouponServiceImpl.class);

    private final CouponRepository couponRepository;
    private final CouponRepositoryMapper couponRepositoryMapper;
    private final MemberRepositoryMapper memberRepositoryMapper;
    private final MemberRepository memberRepository;

    @Autowired
    public CouponServiceImpl(CouponRepository couponRepository, CouponRepositoryMapper couponRepositoryMapper, MemberRepositoryMapper memberRepositoryMapper, MemberRepository memberRepository) {
        this.couponRepository = couponRepository;
        this.couponRepositoryMapper = couponRepositoryMapper;
        this.memberRepositoryMapper = memberRepositoryMapper;
        this.memberRepository = memberRepository;
    }

    @Override
    public Coupon addCoupon(Coupon coupon) throws MandatoryFieldEmptyException {

        logger.info("Add coupon service has been called");

        CouponValidator
                .isNameNullOrEmpty()
                .and(CouponValidator.hasValidLicense())
                .apply(coupon)
                .getException()
                .ifPresent(domainException -> {
                    throw domainException;
                });

        CouponDao savedCoupon = couponRepository
                .save(couponRepositoryMapper.map(coupon));

        Coupon mappedSavedCoupon = couponRepositoryMapper.map(savedCoupon);

        logger.info("Add coupon service has been ended");
        return mappedSavedCoupon;
    }

    @Override
    public Member assignCouponToMember(String memberId, String couponId) {
        logger.info("Assigning coupon to member service has been started");
        Objects.requireNonNull(memberId);
        Objects.requireNonNull(couponId);
        AtomicReference<Member> savedMember = new AtomicReference<>();
        memberRepository
                .findById(Long.valueOf(memberId))
                .ifPresent(memberDao -> {
                    Optional<CouponDao> couponDaoOptional = couponRepository.findById(Long.valueOf(couponId));
                    if (couponDaoOptional.isPresent()) {
                        MemberCouponDao memberCouponDao = new MemberCouponDao();
                        CouponDao couponDao = couponDaoOptional.get();
                        CouponValidator.hasValidLicense().apply(couponRepositoryMapper.map(couponDao));
                        memberCouponDao.setCoupon(couponDao);
                        memberCouponDao.setMember(memberDao);
                        if (memberDao.getMemberCouponSet() == null) {
                            memberDao.setMemberCouponSet(new HashSet<>());
                        }
                        memberDao.getMemberCouponSet().add(memberCouponDao);
                        MemberDao savedMemberDao = memberRepository.save(memberDao);
                        savedMember.set(memberRepositoryMapper.map(savedMemberDao));
                    }
                });
        logger.info("Assigning coupon to member service has been finished");
        return savedMember.get();
    }

    @Override
    public Set<Coupon> findMemberCoupon(String memberId) {
        Objects.requireNonNull(memberId);
        Optional<MemberDao> memberDaoOptional = memberRepository.findById(Long.valueOf(memberId));
        Set<Coupon> couponSet = new HashSet<>();
        LinkedHashSet<Coupon> result = null;
        if (memberDaoOptional.isPresent()) {
            MemberDao memberDao = memberDaoOptional.get();
            Member mappedMember = memberRepositoryMapper.map(memberDao);

            for (MemberCoupon memberCoupon : mappedMember.getMemberCouponSet()) {
                Coupon coupon = memberCoupon.getCoupon();
                if (CouponValidator
                        .hasValidLicense()
                        .apply(coupon)
                        .isValid()) {
                    couponSet.add(coupon);
                }
            }
            couponSet
                    .forEach(coupon -> coupon.setCouponLicenseSet(
                            coupon
                                    .getCouponLicenseSet()
                                    .stream()
                                    .sorted(Comparator.comparing(CouponLicense::getValidUtil).reversed())
                                    .collect(Collectors.toCollection(LinkedHashSet::new))));
            result = couponSet
                    .stream()
                    .sorted(Comparator.comparing(o ->
                            o.getCouponLicenseSet()
                                    .stream()
                                    .findFirst()
                                    .map(CouponLicense::getValidUtil)
                                    .orElse(DateTimeUtil.getCurrentTime())))
                    .collect(Collectors.toCollection(LinkedHashSet::new));

        }
        return result;
    }
}
