package com.personnel.filestorage.user_service.service.impl;

import com.personnel.filestorage.user_service.dto.AccountActivationRequest;
import com.personnel.filestorage.user_service.dto.AuthenticationRequest;
import com.personnel.filestorage.user_service.dto.AuthenticationResponse;
import com.personnel.filestorage.user_service.dto.RegistrationRequest;
import com.personnel.filestorage.user_service.entity.Token;
import com.personnel.filestorage.user_service.entity.User;
import com.personnel.filestorage.user_service.producers.KafkaProducer;
import com.personnel.filestorage.user_service.repository.TokenRepository;
import com.personnel.filestorage.user_service.repository.UserRepository;
import com.personnel.filestorage.user_service.service.JwtService;
import com.personnel.filestorage.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final KafkaProducer kafkaProducer;
    @Value("${application.mailing.frontend.activation-url}")
    private String accountActivationUrl;

    @Override
    public Integer register(RegistrationRequest registrationRequest) {
        var user = User.builder()
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .build();
        User savedUser = userRepository.save(user);
        String activationToken = generateAndSaveActivationToken(savedUser);
        AccountActivationRequest request = AccountActivationRequest.builder()
                .email(savedUser.getEmail())
                .fullName(savedUser.getFullName())
                .token(activationToken)
                .activationUrl(accountActivationUrl)
                .templateType("account_activation")
                .build();
        kafkaProducer.sendMessage(request);
        return savedUser.getId();
    }

    private String generateAndSaveActivationToken(User user) {
        String generatedToken = generateActivationCode(6);
        Token token = Token.builder()
                .code(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);
        return generatedToken;
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        // cryptographically secure the random number generator
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }

    public AuthenticationResponse authenticate(
            AuthenticationRequest authenticationRequest) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        var claims = new HashMap<String, Object>();
        var user = ((User) auth.getPrincipal());
        claims.put("fullName", user.getFullName());
        var jwtToken = jwtService.generateToken(claims, user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public void activateAccount(String code) {
        Token savedToken = tokenRepository.findByCode(code).
                orElseThrow(() -> new RuntimeException("Token not found"));
        if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())) {
            throw new RuntimeException("Token expired");
        }
        var user = userRepository.findById(savedToken.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setEnabled(true);
        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);
        userRepository.save(user);
    }
}
