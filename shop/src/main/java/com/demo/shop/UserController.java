package com.demo.shop;

import com.demo.shop.user.UserService;
import com.demo.shop.user.domain.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService UserService;

    @Autowired
    public UserController(com.demo.shop.user.UserService userService) {
        UserService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public List<UserAccount> getUsers() {
        return UserService.getUsers();
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('user:write')")
    public void registerNewUser(@RequestBody UserAccount user) {
        UserService.createUser(user);
    }

    @DeleteMapping("{userID}")
    @PreAuthorize("hasAuthority('user:write')")
    public void deleteUser(@PathVariable("userID") Long userID) {
        UserService.deleteUser(userID);
    }

    @PutMapping("{userID}")
    @PreAuthorize("hasAuthority('user:write')")
    public void updateUserDetail(
            @PathVariable("userID") long userID,
            @RequestParam UserAccount userAccount) {

        UserService.updateUser(userID, userAccount);

    }
}
