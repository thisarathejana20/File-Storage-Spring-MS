package com.personnel.filestorage.user_service.repository;

import com.personnel.filestorage.user_service.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Integer> {
    Optional<Token> findByCode(String code);
}
