package com.dev.spiderman.user.repository;

import com.dev.spiderman.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Transactional(readOnly = true)
    public boolean existsByUsername(String username) {
        return userJpaRepository.existsByUsername(username);
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Transactional
    public UserEntity create(UserEntity user) {
        UserJpaEntity savedUser = userJpaRepository.save(UserMapper.toJpaEntity(user));
        return UserMapper.toDomain(savedUser);
    }

    @Transactional(readOnly = true)
    public List<UserEntity> findAll() {
        return UserMapper.toDomains(
                userJpaRepository.findAll()
        );
    }

    @Transactional(readOnly = true)
    public Optional<UserEntity> findById(Long id) {
        return userJpaRepository.findById(id)
                .map(UserMapper::toDomain);
    }

    @Transactional(readOnly = true)
    public Optional<UserEntity> findByUsername(String username) {
        return userJpaRepository.findByUsername(username)
                .map(UserMapper::toDomain);
    }

    @Transactional
    public Optional<UserEntity> deleteById(Long id) {
        return userJpaRepository.findById(id)
                .map(user -> {
                    userJpaRepository.delete(user);
                    return UserMapper.toDomain(user);
                });
    }

    @Transactional
    public Optional<UserEntity> updateById(Long id, UserEntity user) {
        return userJpaRepository.findById(id)
                .map(existingUser -> {
                    UserMapper.updateJpaEntity(user, existingUser);
                    return UserMapper.toDomain(existingUser);
                });
    }

    @Transactional(readOnly = true)
    public List<UserEntity> findTrackingCandidate() {
        return userJpaRepository.findByTrackingCandidateTrueAndCurrentTrackerFalse()
                .stream()
                .map(UserMapper::toDomain)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<UserEntity> findCurrentTracker() {
        return userJpaRepository.findByCurrentTrackerTrue()
                .map(UserMapper::toDomain);
    }

    @Transactional
    public void changeCurrentTracker(Long userId) {
        userJpaRepository.findByCurrentTrackerTrue()
                .ifPresent(u -> u.setCurrentTracker(false));

        UserJpaEntity nextTracker = userJpaRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));

        nextTracker.setCurrentTracker(true);
    }

    @Transactional
    public void toggleTrackingCandidate(Long userId) {
        UserJpaEntity currentUser = userJpaRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));

        currentUser.setTrackingCandidate(!currentUser.isTrackingCandidate());
    }
}
