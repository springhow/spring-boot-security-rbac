package com.springhow.examples.springboot.security.databaseuserdetail.controllers;

import com.springhow.examples.springboot.security.databaseuserdetail.domain.entities.UserAccount;
import com.springhow.examples.springboot.security.databaseuserdetail.domain.repositories.UserAccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserAccountRepository userAccountRepository, PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/register")
    public UserAccount register(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        UserAccount userAccount = new UserAccount();
        userAccount.setFirstName(firstName);
        userAccount.setLastName(lastName);
        userAccount.setUsername(username);
        userAccount.setPassword(passwordEncoder.encode(password));
        userAccount.setActive(true);
        return userAccountRepository.save(userAccount);
    }
}
