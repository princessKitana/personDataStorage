package com.pds.core.repository;


import com.pds.core.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByPersonalId(String personalId);

    @Query("select p from Person p where (:firstName is null or p.firstName = :firstName)" +
            "and (:lastName is null or p.lastName = :firstName)" +
            "and (:personalId is null or p.personalId = :personalId)" +
            "and (:gender is null or p.gender = :gender)" +
            "and (:dateOfBirth is null or p.dateOfBirth = :dateOfBirth)")
    List<Person> findByParameters(@Param("firstName") String firstName,
                                  @Param("lastName") String lastName,
                                  @Param("personalId") String personalId,
                                  @Param("gender") String gender,
                                  @Param("dateOfBirth") LocalDate dateOfBirth);

}
