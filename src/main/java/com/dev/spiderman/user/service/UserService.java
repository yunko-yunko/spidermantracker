package com.dev.spiderman.user.service;

import com.dev.spiderman.user.domain.UserEntity;
import com.dev.spiderman.user.repository.UserJpaEntity;
import com.dev.spiderman.user.repository.UserMapper;
import com.dev.spiderman.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserEntity create(UserEntity user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("이미 사용 중인 username입니다.");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 email입니다.");
        }

        return userRepository.create(user);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));
    }

    public UserEntity deleteUser(Long id) {
        return userRepository.deleteById(id)
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));
    }

    public UserEntity updateUser(UserEntity user, Long id) {
        return userRepository.updateById(id, user)
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));
    }

    public UserEntity selectTodayTracker() {
        List<UserEntity> userList = userRepository.findTrackingCandidate();

        if(userList.isEmpty()){
            throw new IllegalStateException("후보자 없음");
        }

        UserEntity nextTracker = userList.get(
                ThreadLocalRandom.current().nextInt(
                        userList.size()
                )
        );

        userRepository.changeCurrentTracker(nextTracker.getId());

        return nextTracker;
    }
}
