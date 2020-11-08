package com.example.jrspringsecurity.auth;

import java.util.Optional;

public interface ApplicationUserDao {
    Optional<ApplicationUser> getApplicationUserByName(String username);
}
