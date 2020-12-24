package com.springhow.examples.springboot.security.databaseuserdetail.security;

import com.springhow.examples.springboot.security.databaseuserdetail.domain.entities.UserAccount;
import com.springhow.examples.springboot.security.databaseuserdetail.domain.repositories.UserAccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUserDetailsService implements UserDetailsService {
    private final
    UserAccountRepository userAccountRepository;

    public DatabaseUserDetailsService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByUsername(username);
        if (userAccount == null) {
            throw new UsernameNotFoundException("User with username [" + username + "] not found in the system");
        }
        return new CustomUserDetails(userAccount);
    }
}
