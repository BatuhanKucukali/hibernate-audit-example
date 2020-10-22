package com.arch.audit;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "revision_info")
@RevisionEntity(AuditRevisionListener.class)
public class AuditRevisionEntity extends DefaultRevisionEntity {

    @Column(name = "user")
    private String user;
}
