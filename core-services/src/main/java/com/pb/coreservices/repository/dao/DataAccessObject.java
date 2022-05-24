package com.pb.coreservices.repository.dao;

import com.pb.coreservices.util.DateTimeUtil;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.sql.Timestamp;


@Getter
@Setter
@MappedSuperclass
@Audited
abstract class DataAccessObject {

    @Column(name = "c_insert_time")
    private Timestamp insertTime;

    @Column(name = "c_update_time")
    private Timestamp lastUpdateTime;

    @Version
    @Column(name = "c_entity_version", nullable = false)
    protected long entityVersion;

    @PrePersist
    public void onPrePersist() {
        setInsertTime(DateTimeUtil.getCurrentTime());
    }

    @PreUpdate
    public void onPreUpdate() {
        entityVersion++;
        setLastUpdateTime(DateTimeUtil.getCurrentTime());
    }

}
