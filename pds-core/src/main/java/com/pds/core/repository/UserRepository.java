package com.pds.core.repository;


import com.pds.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   // Optional<User> findByName(String name);

}
