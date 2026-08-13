package com.dev.spiderman.user.repository;

import com.dev.spiderman.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<UserJpaEntity> findByUsername(String username);

    List<UserJpaEntity> findByTrackingCandidateTrueAndCurrentTrackerFalse();

    Optional<UserJpaEntity> findByCurrentTrackerTrue();
}
