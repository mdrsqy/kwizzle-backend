package com.kwizzle.service;

import com.kwizzle.dto.UserDTO;
import com.kwizzle.model.User;
import com.kwizzle.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return new UserDTO(user);
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = new User(userDTO);
        user = userRepository.save(user);
        return new UserDTO(user);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        user.setStatus(userDTO.getStatus());
        user = userRepository.save(user);
        return new UserDTO(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}