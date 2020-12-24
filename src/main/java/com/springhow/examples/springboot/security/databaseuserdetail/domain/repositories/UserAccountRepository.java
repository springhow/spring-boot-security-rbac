package com.springhow.examples.springboot.security.databaseuserdetail.domain.repositories;

import com.springhow.examples.springboot.security.databaseuserdetail.domain.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
    UserAccount findByUsername(String username);
}
