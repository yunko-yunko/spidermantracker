package com.dev.spiderman.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserEntity {
    private Long id;
    private String username;
    private String password;
    private String email;
    private boolean trackingCandidate;
    private boolean currentTracker;
}
