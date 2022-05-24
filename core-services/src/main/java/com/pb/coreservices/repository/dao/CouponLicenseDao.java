package com.pb.coreservices.repository.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_coupon_license")
public class CouponLicenseDao extends DataAccessObject {

    @Id
    @SequenceGenerator(name = "couponLicenseSeq", sequenceName = "seq_coupon_license")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "c_id")
    private Long id;
    @Column(name = "c_valid_from")
    private Timestamp validFrom;
    @Column(name = "c_valid_until")
    private Timestamp validUtil;

    @ManyToOne
    @JoinColumn(name = "f_coupon_id", nullable = false)
    private CouponDao coupon;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CouponLicenseDao that = (CouponLicenseDao) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

