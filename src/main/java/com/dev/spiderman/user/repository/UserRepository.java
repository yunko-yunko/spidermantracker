package com.dev.spiderman.user.repository;

import com.dev.spiderman.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
}
