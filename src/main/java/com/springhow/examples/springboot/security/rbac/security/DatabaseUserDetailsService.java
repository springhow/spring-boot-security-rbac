package com.springhow.examples.springboot.security.rbac.security;

import com.springhow.examples.springboot.security.rbac.domain.entities.UserAccount;
import com.springhow.examples.springboot.security.rbac.domain.entities.UserRoleToPrivilege;
import com.springhow.examples.springboot.security.rbac.domain.entities.UserToRole;
import com.springhow.examples.springboot.security.rbac.domain.repositories.UserAccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Component
public class DatabaseUserDetailsService implements UserDetailsService {

    private final
    UserAccountRepository userAccountRepository;

    public DatabaseUserDetailsService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByUsername(username);
        if (userAccount == null) {
            throw new UsernameNotFoundException("User with username [" + username + "] not found in the system");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();

        for (UserToRole userToRole : userAccount.getUserToRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userToRole.getRole().getRoleName()));
            for (UserRoleToPrivilege userRoleToPrivilege : userToRole.getRole().getUserRoleToPrivileges()) {
                authorities.add(new SimpleGrantedAuthority(userRoleToPrivilege.getPrivilege().getPrivilegeName()));
            }
        }

        return new CustomUserDetails(userAccount.getUsername(), userAccount.getPassword(), userAccount.isActive(), authorities);
    }
}
