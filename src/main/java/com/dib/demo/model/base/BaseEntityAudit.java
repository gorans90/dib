package com.dib.demo.model.base;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

/**
 * Base entity audit database model
 */

@Getter
@MappedSuperclass
public abstract class BaseEntityAudit extends BaseEntity {

    /**
     * creation date
     */
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    /**
     * update date
     */
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @PrePersist
    public void setCreationDate() {
        this.createdDate = LocalDateTime.now();
    }

    @PreUpdate
    public void setChangeDate() {
        this.updatedDate = LocalDateTime.now();
    }

}