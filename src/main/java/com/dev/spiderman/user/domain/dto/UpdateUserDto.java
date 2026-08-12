package com.dev.spiderman.user.domain.dto;

import com.dev.spiderman.user.domain.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateUserDto {
    private String username;
    private String password;
    private String email;

    public UserEntity toDomain() {
        return UserEntity.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();
    }
}
