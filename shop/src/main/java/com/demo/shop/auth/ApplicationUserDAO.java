package com.demo.shop.auth;

import com.demo.shop.user.domain.UserAccount;

import java.util.Optional;

public interface ApplicationUserDAO {

    Optional<ApplicationUser> selectApplicationUserByUsername(String username);

}
