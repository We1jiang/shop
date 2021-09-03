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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<UserAccount> getUsers() {
        return UserService.getUsers();
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('admin:write')")
    public void registerNewUser(@RequestBody UserAccount user) {
        UserService.createUser(user);
    }

    @DeleteMapping("{productID}")
    @PreAuthorize("hasAuthority('admin:write')")
    public void deleteUser(@PathVariable("productID") Long id) {
        UserService.deleteUser(id);
    }

    @PutMapping("{productID}")
    @PreAuthorize("hasAuthority('admin:write')")
    public void updateUserDetail(
            @PathVariable("productID") long productID,
            @RequestParam UserAccount userAccount) {

        UserService.updateUser(productID, userAccount);

    }
}
