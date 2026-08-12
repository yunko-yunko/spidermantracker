package com.dev.spiderman;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/api/root"))
@RequiredArgsConstructor()
public class rootController {

    @GetMapping("/status")
    public String status() {
        return "OK";
    }

}
