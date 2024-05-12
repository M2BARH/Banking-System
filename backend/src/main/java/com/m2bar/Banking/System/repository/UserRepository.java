package com.m2bar.Banking.System.repository;

import com.m2bar.Banking.System.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByIdentityNumber(String identityNumber);

    User findByEmailAddress(String emailAddress);
}
