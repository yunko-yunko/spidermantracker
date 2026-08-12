package com.dev.spiderman.user.controller;

import com.dev.spiderman.user.domain.UserEntity;
import com.dev.spiderman.user.domain.dto.CreateUserDto;
import com.dev.spiderman.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody CreateUserDto dto) {
        UserEntity newUser = userService.create(dto.toDomain());
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
