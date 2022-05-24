package com.pb.coreservices.repository;

import com.pb.coreservices.repository.dao.CouponDao;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends PagingAndSortingRepository<CouponDao, Long> {
}
