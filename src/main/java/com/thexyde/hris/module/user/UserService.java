package com.thexyde.hris.module.user;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<User> getAllUsers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return userRepository.findAll(pageable);
    }

    public User getUserById(UUID userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User registerUser(UserDTO userDTO) {
        String password = passwordEncoder.encode(userDTO.getPassword());

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(password);
        user.setRoleId(userDTO.getRoleId());
        user.setClientId(userDTO.getClientId());
        user.setDivisionId(userDTO.getDivisionId());
        user.setDepartmentId(userDTO.getDepartmentId());
        user.setJobUnitId(userDTO.getJobUnitId());
        user.setJobLevelId(userDTO.getJobLevelId());
        user.setJobTitleId(userDTO.getJobTitleId());

        return userRepository.save(user);
    }

}
