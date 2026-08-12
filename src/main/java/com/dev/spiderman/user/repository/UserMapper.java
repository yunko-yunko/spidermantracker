package com.dev.spiderman.user.repository;

import com.dev.spiderman.user.domain.UserEntity;

import java.util.List;
import java.util.Objects;

/**
 * Domain user와 JPA user 사이의 변환을 담당한다.
 */
public final class UserMapper {

    private UserMapper() {
    }

    public static UserEntity toDomain(UserJpaEntity userJpaEntity) {
        if (userJpaEntity == null) {
            return null;
        }

        return new UserEntity(
                userJpaEntity.getId(),
                userJpaEntity.getUsername(),
                userJpaEntity.getPassword(),
                userJpaEntity.getEmail()
        );
    }

    public static UserJpaEntity toJpaEntity(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        return new UserJpaEntity(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getEmail()
        );
    }

    public static List<UserEntity> toDomains(List<UserJpaEntity> userJpaEntities) {
        if (userJpaEntities == null) {
            return List.of();
        }

        return userJpaEntities.stream()
                .filter(Objects::nonNull)
                .map(UserMapper::toDomain)
                .toList();
    }

    public static List<UserJpaEntity> toJpaEntities(List<UserEntity> userEntities) {
        if (userEntities == null) {
            return List.of();
        }

        return userEntities.stream()
                .filter(Objects::nonNull)
                .map(UserMapper::toJpaEntity)
                .toList();
    }

    /**
     * JPA가 관리 중인 엔티티의 변경 가능한 사용자 정보를 갱신한다.
     * 식별자는 영속성 컨텍스트가 관리하므로 변경하지 않는다.
     */
    public static void updateJpaEntity(UserEntity source, UserJpaEntity target) {
        Objects.requireNonNull(source, "source must not be null");
        Objects.requireNonNull(target, "target must not be null");

        target.setUsername(source.getUsername());
        target.setPassword(source.getPassword());
        target.setEmail(source.getEmail());
    }
}
