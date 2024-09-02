package com.thexyde.hris.module.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.thexyde.hris.entity.User;
import com.thexyde.hris.module.role.RoleRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Autowired
    public RoleRepository roleRepository;

    public User registerUser(UserDTO userDTO) {
        String password = passwordEncoder.encode(userDTO.getPassword());
        User user = new User(userDTO.getName(), userDTO.getEmail(), password, userDTO.getRoleId(), null);
        return userRepository.save(user);
    }

    // Other methods like finding a user, updating, etc.
}
