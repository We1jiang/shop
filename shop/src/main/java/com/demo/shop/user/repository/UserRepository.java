package com.demo.shop.user.repository;

import com.demo.shop.user.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, Long> {

    @Query("SELECT u FROM UserAccount u Where u.email = ?1")
    Optional<UserAccount> findUserByEmail(String email);

    @Query("SELECT u FROM UserAccount u Where u.userName = ?1")
    Optional<UserAccount> findUserByUserName(String username);
}