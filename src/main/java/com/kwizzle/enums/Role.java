package com.kwizzle.enums;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public enum Role {
    ADMIN,
    USER;

    // Add this method to return authorities based on roles
    public Collection<GrantedAuthority> getAuthorities() {
        switch (this) {
            case ADMIN:
                return Collections.singletonList(new SimpleGrantedAuthority("ADMIN"));
            case USER:
                return Collections.singletonList(new SimpleGrantedAuthority("USER"));
            default:
                return Collections.emptyList();
        }
    }
}