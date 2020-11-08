package com.example.jrspringsecurity.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import static com.example.jrspringsecurity.security.ApplicationUserRole.*;

import java.util.List;
import java.util.Optional;

@Repository()
public class ApplicationUserDaoImpl implements ApplicationUserDao {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<UserDetails> getApplicationUserByName(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<UserDetails> getApplicationUsers() {
        List<UserDetails> applicationUsers = Lists.newArrayList(
                new ApplicationUser(
                        "student",
                        passwordEncoder.encode("password"),
                        STUDENT.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        "admin",
                        passwordEncoder.encode("password"),
                        ADMIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        "admin-trainee",
                        passwordEncoder.encode("password"),
                        ADMINTRAINEE.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        );

        return applicationUsers;
    }
}
