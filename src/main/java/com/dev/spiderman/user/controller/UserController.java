package com.dev.spiderman.user.controller;

import com.dev.spiderman.user.domain.UserEntity;
import com.dev.spiderman.user.domain.dto.CreateUserDto;
import com.dev.spiderman.user.domain.dto.UpdateUserDto;
import com.dev.spiderman.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Long id){
        UserEntity user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping()
    public ResponseEntity<UserEntity> getUserByUsername(@RequestParam String username){
        UserEntity user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody CreateUserDto dto) {
        UserEntity newUser = userService.create(dto.toDomain());
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody UpdateUserDto dto, @PathVariable Long id) {
        UserEntity updatedUser = userService.updateUser(dto.toDomain(), id);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        UserEntity deletedUser = userService.deleteUser(id);
        return ResponseEntity.ok(deletedUser);
    }
}
