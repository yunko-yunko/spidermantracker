package com.dev.spiderman.location.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LocationRequest {
    private Long userId;
    private Double latitude;
    private Double longitude;
}
