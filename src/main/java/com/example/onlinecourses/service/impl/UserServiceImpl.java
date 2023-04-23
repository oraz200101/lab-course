package com.example.onlinecourses.service.impl;

import com.example.onlinecourses.config.JwtGenerator;
import com.example.onlinecourses.exception.domain.CustomValidationException;
import com.example.onlinecourses.exception.domain.UserNotFoundByEmailException;
import com.example.onlinecourses.mapper.UserMapper;
import com.example.onlinecourses.models.dto.AuthResponseDto;
import com.example.onlinecourses.models.dto.UserLoginDto;
import com.example.onlinecourses.models.dto.UserRegisterDto;
import com.example.onlinecourses.models.entities.QUser;
import com.example.onlinecourses.models.entities.User;
import com.example.onlinecourses.models.enums.Role;
import com.example.onlinecourses.repository.UserRepository;
import com.example.onlinecourses.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

import static com.example.onlinecourses.constants.validation.UserValidationConstants.USER_EMAIL_ALREADY_EXISTS_MESSAGE;
import static com.example.onlinecourses.constants.validation.UserValidationConstants.USER_PASSWORDS_NOT_EQUAL_MESSAGE;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthResponseDto loginUser(UserLoginDto userLoginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDto.getEmail(),
                        userLoginDto.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new AuthResponseDto(token);
    }

    @Override
    @Transactional
    public void registerUser(UserRegisterDto userRegisterDto) {
        if (userRepository.existsByEmail(userRegisterDto.getEmail())) {
            throw new CustomValidationException(USER_EMAIL_ALREADY_EXISTS_MESSAGE, null);
        } else if (!userRegisterDto.getPassword().equals(userRegisterDto.getMatchingPassword())) {
            throw new CustomValidationException(USER_PASSWORDS_NOT_EQUAL_MESSAGE, null);
        }

        User user = userMapper.mapToUserEntity(userRegisterDto);
        user.setRoles(new HashSet<>());
        user.getRoles().add(Role.USER);
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundByEmailException::new);
    }

    public User getById(Long id) {
        return userRepository.findOne(QUser.user.id.eq(id)).orElseThrow(() -> new RuntimeException("ffv"));
    }
}
