package com.dev.spiderman.user.controller;

import com.dev.spiderman.user.domain.UserEntity;
import com.dev.spiderman.user.domain.dto.CreateUserDto;
import com.dev.spiderman.user.domain.dto.UpdateUserDto;
import com.dev.spiderman.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<?> getAllUsers() {
        List<UserEntity> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Long id){
        UserEntity user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserEntity> getUserByUsername(@PathVariable String username){
        UserEntity user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/todaytracker")
    public ResponseEntity<?> getTodayTracker(){
        UserEntity tracker = userService.selectTodayTracker();
        return ResponseEntity.ok(tracker);
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

    @PostMapping("/toggle/{id}")
    public ResponseEntity<?> toggleTrackingCandidate(@PathVariable Long id) {
        UserEntity updatedUser = userService.toggleTrackingCandidate(id);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        UserEntity deletedUser = userService.deleteUser(id);
        return ResponseEntity.ok(deletedUser);
    }
}
