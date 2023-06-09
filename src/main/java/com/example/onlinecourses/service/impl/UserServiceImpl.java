package com.example.onlinecourses.service.impl;

import com.example.onlinecourses.config.JwtGenerator;
import com.example.onlinecourses.exception.domain.CustomValidationException;
import com.example.onlinecourses.exception.domain.NotFoundException;
import com.example.onlinecourses.mapper.UserMapper;
import com.example.onlinecourses.models.dto.AuthResponseDto;
import com.example.onlinecourses.models.dto.UserLoginDto;
import com.example.onlinecourses.models.dto.UserRegisterDto;
import com.example.onlinecourses.models.dto.UserResponseDto;
import com.example.onlinecourses.models.entities.User;
import com.example.onlinecourses.models.enums.Role;
import com.example.onlinecourses.repository.UserRepository;
import com.example.onlinecourses.service.AuthenticationFacade;
import com.example.onlinecourses.service.UserService;
import com.example.onlinecourses.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
import java.util.List;
import java.util.Set;


import static com.example.onlinecourses.constants.validation.UserValidationConstants.USER_EMAIL_ALREADY_EXISTS_MESSAGE;
import static com.example.onlinecourses.constants.validation.UserValidationConstants.USER_PASSWORDS_NOT_EQUAL_MESSAGE;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;
    private final AuthenticationManager authenticationManager;

    private final AuthenticationFacade authenticationFacade;


    @Override
    public AuthResponseDto loginUser(UserLoginDto userLoginDto) {
        return allLogin(userLoginDto.getEmail(), userLoginDto.getPassword());
    }

    private AuthResponseDto allLogin(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new AuthResponseDto(token, authenticationFacade.getCurrentPrincipal().getRoles());
    }

    @Override
    @Transactional
    public void registerUser(UserRegisterDto userRegisterDto) {
        validateRegister(userRegisterDto);
        User user = userMapper.mapToUserEntity(userRegisterDto);
        user.setRoles(new HashSet<>());
        user.getRoles().add(Role.USER);
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        user.setDateOfBirth(DateUtils.parseToLocalDateTime(userRegisterDto.getDateOfBirth()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void createTeacher(UserRegisterDto userRegisterDto) {
        validateRegister(userRegisterDto);
        User user = userMapper.mapToUserEntity(userRegisterDto);
        user.setRoles(new HashSet<>());
        user.getRoles().add(Role.TEACHER);
        user.setEnabled(true);
        user.setDateOfBirth(DateUtils.parseToLocalDateTime(userRegisterDto.getDateOfBirth()));
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public String updateProfile(UserRegisterDto userRegisterDto) {
        Long userId = authenticationFacade.getCurrentPrincipal().getId();
        User user = userRepository.findById(userId).orElseThrow();
        user = userMapper.mapToUserEntity(user, userRegisterDto);
        userRepository.save(user);
        return "Your account information is updated";
    }

    @Override
    public Page<UserResponseDto> getTeachers(Pageable pageable) {
        List<User> users = userRepository.findAll();
        Set<Role> roles = new HashSet<>();
        roles.add(Role.TEACHER);
        List<User> teachers = users.stream().filter(u -> u.getRoles().equals(roles)).toList();
        return new PageImpl<>(userMapper.mapToUserResponseDtos(teachers), pageable, teachers.size());
    }


    @Override
    public UserResponseDto getById(Long id) {
        return userMapper.mapToUserResponseDto(userRepository.findById(id).orElseThrow(() -> new NotFoundException("not found " + id)));
    }


    private void validateRegister(UserRegisterDto userRegisterDto) {
        if (userRepository.existsByEmail(userRegisterDto.getEmail())) {
            throw new CustomValidationException(USER_EMAIL_ALREADY_EXISTS_MESSAGE, null);
        } else if (!userRegisterDto.getPassword().equals(userRegisterDto.getMatchingPassword())) {
            throw new CustomValidationException(USER_PASSWORDS_NOT_EQUAL_MESSAGE, null);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("user not found by email"));
    }


}
