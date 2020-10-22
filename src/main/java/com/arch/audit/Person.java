package com.arch.audit;

import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    @Audited
    @Column
    private String fullName;

    @Audited
    @Column
    private String email;

    public Person() {
    }

    public Person(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }
}
