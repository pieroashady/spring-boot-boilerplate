package com.thexyde.hris.module.session;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thexyde.hris.entity.Session;
import com.thexyde.hris.exception.RefreshTokenException;
import com.thexyde.hris.module.user.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class SessionService {

    private long sessionDurationMs = 1000 * 3600 * 24 * 1;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<Session> findByToken(String token) {
        return sessionRepository.findByToken(token);
    }

    public Session createRefreshToken(UUID userId) {
        Session getSession = sessionRepository.findByUserId(userId).get();

        Session session = new Session();
        session.setId(getSession.getId());
        session.setUser(userRepository.findById(userId).get());
        session.setExpiresAt(Instant.now().plusMillis(sessionDurationMs));
        session.setIssuedAt(new Date().toInstant());
        session.setToken(UUID.randomUUID().toString());

        session = sessionRepository.save(session);
        return session;
    }

    public Session verifyExpiration(Session token) {
        if (token.getExpiresAt().compareTo(Instant.now()) < 0) {
            sessionRepository.delete(token);
            throw new RefreshTokenException(token.getToken(), "Refresh token was expired. Please sign in again.");
        }

        return token;
    }

    @Transactional
    public int deleteByUserId(UUID userId) {
        return sessionRepository.deleteByUser(userRepository.findById(userId).get());
    }
}
