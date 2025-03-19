package com.kwizzle.service;

import com.kwizzle.config.JwtUtil;
import com.kwizzle.dto.UserDTO;
import com.kwizzle.model.User;
import com.kwizzle.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
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
        User user = new User();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPasswordHash(passwordEncoder.encode(userDTO.getEmail())); // Hash password sebelum disimpan
        user.setRole(userDTO.getRole());
        user.setStatus(userDTO.getStatus());
        user.setRegisteredAt(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setProfile(userDTO.getProfile());

        user = userRepository.save(user);

        String token = jwtUtil.generateToken(user.getUsername()); // Generate token setelah user dibuat

        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getStatus(),
                user.getRegisteredAt(),
                user.getLastLogin(),
                user.getProfile(),
                token
        );
    }

    public Optional<UserDTO> updateUser(Long id, UserDTO userDTO) {
        return userRepository.findById(id).map(user -> {
            user.setName(userDTO.getName());
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            user.setRole(userDTO.getRole());
            user.setStatus(userDTO.getStatus());
            user.setLastLogin(LocalDateTime.now());
            user.setProfile(userDTO.getProfile());

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
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (passwordEncoder.matches(password, user.getPasswordHash())) {
            String token = jwtUtil.generateToken(user.getUsername()); // Generate token saat login
            return new UserDTO(
                    user.getId(),
                    user.getName(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getRole(),
                    user.getStatus(),
                    user.getRegisteredAt(),
                    user.getLastLogin(),
                    user.getProfile(),
                    token
            );
        } else {
            throw new IllegalArgumentException("Invalid password");
        }
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getStatus(),
                user.getRegisteredAt(),
                user.getLastLogin(),
                user.getProfile()
        );
    }
}