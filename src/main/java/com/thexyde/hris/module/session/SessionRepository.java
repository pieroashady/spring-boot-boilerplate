package com.thexyde.hris.module.session;

import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.thexyde.hris.entity.Session;
import com.thexyde.hris.entity.User;

public interface SessionRepository extends JpaRepository<Session, UUID> {

    Optional<Session> findByToken(String token);

    Optional<Session> findByUserId(UUID userId);

    @Modifying
    int deleteByUser(User user);

}
