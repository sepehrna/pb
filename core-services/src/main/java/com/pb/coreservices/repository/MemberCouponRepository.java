package com.pb.coreservices.repository;

import com.pb.coreservices.repository.dao.MemberCouponDao;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberCouponRepository extends PagingAndSortingRepository<MemberCouponDao, Long> {
}
