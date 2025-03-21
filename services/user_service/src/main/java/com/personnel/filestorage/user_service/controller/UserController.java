package com.personnel.filestorage.user_service.controller;

import com.personnel.filestorage.user_service.dto.AuthenticationRequest;
import com.personnel.filestorage.user_service.dto.AuthenticationResponse;
import com.personnel.filestorage.user_service.dto.RegistrationRequest;
import com.personnel.filestorage.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Integer> register(@RequestBody @Valid RegistrationRequest registrationRequest) {
        return ResponseEntity.ok(userService.register(registrationRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(userService.authenticate(authenticationRequest));
    }

    @GetMapping("/activate-account")
    public void confirm(@RequestParam("token") String token) {
            userService.activateAccount(token);
    }
}
