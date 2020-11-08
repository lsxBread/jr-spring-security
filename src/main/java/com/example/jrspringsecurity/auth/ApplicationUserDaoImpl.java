package com.example.jrspringsecurity.auth;

import com.example.jrspringsecurity.entities.Authority;
import com.example.jrspringsecurity.entities.User;
import com.example.jrspringsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository()
public class ApplicationUserDaoImpl implements ApplicationUserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<UserDetails> getApplicationUserByName(String username) {
        User user = userRepository.findByUsername(username);

        UserDetails userDetails = new ApplicationUser(
                user.getUsername(),
                user.getEncodedPassword(),
                generateGrantedAuthority(user.getAuthorities()),
                true,
                true,
                true,
                true
        );

        return Optional.of(userDetails);
    }

    private Set<SimpleGrantedAuthority> generateGrantedAuthority(Set<Authority> authorities) {
        Set<SimpleGrantedAuthority> permissions = authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getPermission()))
                .collect(Collectors.toSet());
        return permissions;
    }
}
