package com.pb.coreservices.repository;

import com.pb.coreservices.repository.dao.MemberDao;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends PagingAndSortingRepository<MemberDao, Long> {
}
