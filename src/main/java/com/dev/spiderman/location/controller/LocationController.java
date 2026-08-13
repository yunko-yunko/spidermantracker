package com.dev.spiderman.location.controller;

import com.dev.spiderman.location.dto.LocationRequest;
import com.dev.spiderman.location.dto.LocationResponse;
import com.dev.spiderman.location.service.LocationWebSocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/location")
@RequiredArgsConstructor
public class LocationController {

    private final LocationWebSocketService locationWebSocketService;

    @PostMapping
    public ResponseEntity<Void> updateLocation(@RequestBody LocationRequest request) {
        LocationResponse response = new LocationResponse(
                request.getUserId(),
                request.getLatitude(),
                request.getLongitude(),
                System.currentTimeMillis()
        );

        locationWebSocketService.sendLocation(response);

        return ResponseEntity.ok().build();
    }
}
