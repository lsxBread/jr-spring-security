package com.example.jrspringsecurity.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.jrspringsecurity.security.ApplicationUserAuthority.*;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(STUDENT_READ));

    private final Set<ApplicationUserAuthority> applicationUserAuthorities;

    ApplicationUserRole(Set<ApplicationUserAuthority> applicationUserAuthorities) {
        this.applicationUserAuthorities = applicationUserAuthorities;
    }

    public Set<ApplicationUserAuthority> getApplicationUserAuthorities() {
        return applicationUserAuthorities;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> authorities = getApplicationUserAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
