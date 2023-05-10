package com.example.onlinecourses.service.impl;

import com.example.onlinecourses.models.entities.User;
import com.example.onlinecourses.repository.UserRepository;
import com.example.onlinecourses.service.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.security.Security;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationFacadeImpl implements AuthenticationFacade {
    private final UserRepository repository;

    @Override
    public User getCurrentPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = repository.findByEmail(authentication.getName());
        if (user.isEmpty()) {
            return null;
        } else {
            return user.orElseThrow();
        }
    }
}
