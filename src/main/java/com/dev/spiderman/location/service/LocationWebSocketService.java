package com.dev.spiderman.location.service;

import com.dev.spiderman.location.dto.LocationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationWebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public void sendLocation(LocationResponse location) {
        messagingTemplate.convertAndSend(
                "/topic/location",
                location
        );
    }
}
