package com.demo.shop.auth;

import com.demo.shop.user.domain.UserAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class ApplicationUser implements UserDetails {

    private  final  String password;
    private  final  String username;
    private  final Set<SimpleGrantedAuthority> grantedAuthority;

    private  final  boolean isAccountNonExpired;
    private  final  boolean isAccountNonLocked;
    private  final  boolean isCredentialsNonExpired;
    private  final  boolean isEnabled;

    public ApplicationUser(
            String password,
            String username,
            Set<SimpleGrantedAuthority> grantedAuthority,
            boolean isAccountNonExpired,
            boolean isAccountNonLocked,
            boolean isCredentialsNonExpired,
            boolean isEnabled) {

        this.password = password;
        this.username = username;
        this.grantedAuthority = grantedAuthority;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public ApplicationUser(UserAccount user) {
        this.username=user.getUserName();
        this.password=user.getPassword();
        this.isAccountNonExpired=user.isAccountNonExpired();
        this.isAccountNonLocked=user.isAccountNonLocked();
        this.isEnabled=user.isEnabled();
        this.isCredentialsNonExpired=user.isCredentialsNonExpired();
        this.grantedAuthority=Arrays.stream(user.getRole().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthority;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
