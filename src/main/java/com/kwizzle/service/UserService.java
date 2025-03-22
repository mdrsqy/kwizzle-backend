package com.kwizzle.service;

import com.kwizzle.config.JwtUtil;
import com.kwizzle.dto.UserDTO;
import com.kwizzle.model.User;
import com.kwizzle.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*\\d).{6,}$";

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(this::convertToDTO);
    }

    public Optional<UserDTO> getUserByUsername(String username) {
        return userRepository.findByUsername(username).map(this::convertToDTO);
    }

    public Optional<UserDTO> getUserByEmail(String email) {
        return userRepository.findByEmail(email).map(this::convertToDTO);
    }

    public UserDTO createUser(UserDTO userDTO) {
        if (!isValidPassword(userDTO.getPassword())) {
            throw new IllegalArgumentException("Password must be at least 6 characters long, contain at least 1 uppercase letter and 1 number.");
        }

        String username = userDTO.getUsername().toLowerCase();

        if (userRepository.findByUsername(username).isPresent() &&
                userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Username and email are already in use.");
        } else if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username is already in use.");
        } else if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already in use.");
        }

        // Create and save the user
        User user = new User();
        user.setName(userDTO.getName());
        user.setUsername(username);  // Save username in lowercase
        user.setEmail(userDTO.getEmail());
        user.setPasswordHash(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(userDTO.getRole());
        user.setStatus(userDTO.getStatus());
        user.setRegisteredAt(LocalDateTime.now());
        user.setLastLogin(null);

        user = userRepository.save(user);

        String token = generateUserToken(user);
        return convertToDTO(user, token);
    }

    public Optional<UserDTO> updateUser(Long id, UserDTO userDTO) {
        return userRepository.findById(id).map(user -> {
            user.setName(userDTO.getName());
            user.setUsername(userDTO.getUsername().toLowerCase());
            user.setEmail(userDTO.getEmail());
            user.setRole(userDTO.getRole());
            user.setStatus(userDTO.getStatus());

            user = userRepository.save(user);
            return convertToDTO(user);
        });
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public UserDTO loginUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Username not found"));

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new IllegalArgumentException("Incorrect password");
        }

        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        String token = generateUserToken(user);
        return convertToDTO(user, token);
    }

    private String generateUserToken(User user) {
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPasswordHash())
                .roles(user.getRole().name())
                .build();

        return jwtUtil.generateToken(userDetails);
    }

    private UserDTO convertToDTO(User user, String token) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getPasswordHash(),
                user.getRole(),
                user.getStatus(),
                user.getRegisteredAt(),
                user.getLastLogin(),
                token
        );
    }

    private UserDTO convertToDTO(User user) {
        return convertToDTO(user, null);
    }

    private boolean isValidPassword(String password) {
        return Pattern.matches(PASSWORD_PATTERN, password);
    }
}