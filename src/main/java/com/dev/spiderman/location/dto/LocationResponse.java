package com.dev.spiderman.location.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LocationResponse {
    private Long userId;
    private Double latitude;
    private Double longitude;
    private Long timestamp;
}
