//package com.kwizzle.dto;
//
//import com.kwizzle.enums.Role;
//import com.kwizzle.enums.UserStatus;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.time.LocalDateTime;
//
//public class UserDTO {
//    private Long id;
//    private String name;
//    private String username;
//    private String email;
//    private Role role;
//    private UserStatus status;
//    private LocalDateTime registeredAt;
//    private LocalDateTime lastLogin;
//    private String profile;
//    private String token;  // Menambahkan token
//
//    public UserDTO() {}
//
//    public UserDTO(Long id, String name, String username, String email, Role role, UserStatus status, LocalDateTime registeredAt, LocalDateTime lastLogin, String profile) {
//        this.id = id;
//        this.name = name;
//        this.username = username;
//        this.email = email;
//        this.role = role;
//        this.status = status;
//        this.registeredAt = registeredAt;
//        this.lastLogin = lastLogin;
//        this.profile = profile;
//    }
//
//    public UserDTO(Long id, String name, String username, String email, Role role, UserStatus status, LocalDateTime registeredAt, LocalDateTime lastLogin, String profile, String token) {
//        this(id, name, username, email, role, status, registeredAt, lastLogin, profile);
//        this.token = token;  // Menginisialisasi token
//    }
//
//    public UserDTO(Long id, String name, UserDetails username, String email, Role role, UserStatus status, LocalDateTime registeredAt, LocalDateTime lastLogin, String profile) {
//    }
//
//    public UserDTO(Long id, String name, UserDetails username, String email, Role role, UserStatus status, LocalDateTime registeredAt, LocalDateTime lastLogin, String profile, String token) {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//
//    public UserStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(UserStatus status) {
//        this.status = status;
//    }
//
//    public LocalDateTime getRegisteredAt() {
//        return registeredAt;
//    }
//
//    public void setRegisteredAt(LocalDateTime registeredAt) {
//        this.registeredAt = registeredAt;
//    }
//
//    public LocalDateTime getLastLogin() {
//        return lastLogin;
//    }
//
//    public void setLastLogin(LocalDateTime lastLogin) {
//        this.lastLogin = lastLogin;
//    }
//
//    public String getProfile() {
//        return profile;
//    }
//
//    public void setProfile(String profile) {
//        this.profile = profile;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//}

package com.kwizzle.dto;

import com.kwizzle.enums.Role;
import com.kwizzle.enums.UserStatus;
import java.time.LocalDateTime;

public class UserDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private Role role;
    private UserStatus status;
    private LocalDateTime registeredAt;
    private LocalDateTime lastLogin;
    private String profile;
    private String token;  // Token for JWT authentication

    public UserDTO() {}

    public UserDTO(Long id, String name, String username, String email, Role role, UserStatus status, LocalDateTime registeredAt, LocalDateTime lastLogin, String profile) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.role = role;
        this.status = status;
        this.registeredAt = registeredAt;
        this.lastLogin = lastLogin;
        this.profile = profile;
    }

    public UserDTO(Long id, String name, String username, String email, Role role, UserStatus status, LocalDateTime registeredAt, LocalDateTime lastLogin, String profile, String token) {
        this(id, name, username, email, role, status, registeredAt, lastLogin, profile);
        this.token = token;  // Assigning the token value
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}