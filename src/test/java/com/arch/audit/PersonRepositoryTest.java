package com.arch.audit;

import org.hibernate.envers.RevisionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void save_ShouldSaveInitialRevision() {
        // arrange
        Person person = new Person("John Doe", "john@doe.com");

        // act
        personRepository.save(person);

        // assertion
        int revisionType = RevisionType.ADD.ordinal();

        Query nativeQuery = entityManager.createNativeQuery("select count(*) from person_audit where revision_type = :revisionType");
        nativeQuery.setParameter("revisionType", revisionType);
        int result = ((Number) nativeQuery.getSingleResult()).intValue();

        assertTrue(result > 0);
    }

    @Test
    void save_ShouldSaveModifiedRevision() {
        // arrange
        Person person = new Person("John Doe", "john@doe.com");
        person = personRepository.save(person);

        person.setFullName("Jane Doe");

        // act
        personRepository.save(person);

        // assertion
        int revisionType = RevisionType.MOD.ordinal();

        Query nativeQuery = entityManager.createNativeQuery("select count(*) from person_audit where revision_type = :revisionType");
        nativeQuery.setParameter("revisionType", revisionType);
        int result = ((Number) nativeQuery.getSingleResult()).intValue();

        assertTrue(result > 0);
    }
}
