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
@Table(name = "t_member")
public class MemberDao extends DataAccessObject {

    @Id
    @SequenceGenerator(name = "memberSeq", sequenceName = "seq_member")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "c_id")
    private Long id;
    @Column(name = "c_name", nullable = false)
    private String name;

    @Column(name = "c_last_name", nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MemberCouponDao> memberCouponSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MemberDao memberDao = (MemberDao) o;
        return id != null && Objects.equals(id, memberDao.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
