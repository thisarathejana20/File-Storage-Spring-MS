package com.personnel.filestorage.user_service.service;

import com.personnel.filestorage.user_service.dto.AuthenticationRequest;
import com.personnel.filestorage.user_service.dto.AuthenticationResponse;
import com.personnel.filestorage.user_service.dto.RegistrationRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService {
    Integer register(@Valid RegistrationRequest registrationRequest);
    void activateAccount(String code);
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
