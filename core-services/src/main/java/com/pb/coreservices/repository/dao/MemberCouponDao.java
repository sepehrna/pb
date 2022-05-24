package com.pb.coreservices.repository.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_member_coupon")
public class MemberCouponDao extends DataAccessObject {

    @Id
    @SequenceGenerator(name = "memberCouponSeq", sequenceName = "seq_member_coupon")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "c_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "f_coupon_id", nullable = false)
    private CouponDao coupon;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "f_member_id", nullable = false)
    private MemberDao member;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MemberCouponDao that = (MemberCouponDao) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
