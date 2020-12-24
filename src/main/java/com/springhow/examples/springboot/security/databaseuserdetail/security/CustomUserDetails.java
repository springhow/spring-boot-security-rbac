package com.springhow.examples.springboot.security.databaseuserdetail.security;

import com.springhow.examples.springboot.security.databaseuserdetail.domain.entities.UserAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final UserAccount userAccount;


    public CustomUserDetails(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "USER";
            }
        });
    }


    @Override
    public String getUsername() {
        return userAccount.getUsername();
    }

    @Override
    public String getPassword() {
        return userAccount.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return userAccount.isActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return userAccount.isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return userAccount.isActive();
    }

    @Override
    public boolean isEnabled() {
        return userAccount.isActive();
    }
}
