package com.pb.coreservices.repository.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_coupon")
public class CouponDao extends DataAccessObject {

    @Id
    @SequenceGenerator(name = "couponSeq", sequenceName = "seq_coupon")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "c_id")
    private Long id;
    @Column(name = "c_name")
    private String name;

    @OneToMany(mappedBy = "coupon", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CouponLicenseDao> couponLicenseSet;

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MemberCouponDao> memberCouponSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CouponDao couponDao = (CouponDao) o;
        return id != null && Objects.equals(id, couponDao.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
