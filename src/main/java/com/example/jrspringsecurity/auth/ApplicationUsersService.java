package com.example.jrspringsecurity.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static com.example.jrspringsecurity.security.ApplicationUserRole.*;

@Service
public class ApplicationUsersService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ArrayList<UserDetails> users = new ArrayList<>();

        UserDetails student = User.builder()
                .username("student")
                .password(passwordEncoder.encode("password"))
                .authorities(STUDENT.getGrantedAuthorities())
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("password"))
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails adminTrainee = User.builder()
                .username("admin-trainee")
                .password(passwordEncoder.encode("password"))
                .authorities(ADMINTRAINEE.getGrantedAuthorities())
                .build();

        users.add(student);
        users.add(admin);
        users.add(adminTrainee);

        return users.stream()
                .filter(userDetails -> username.equals(userDetails.getUsername()))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username))
        );
    }
}
