package com.thexyde.hris.module.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thexyde.hris.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

}
