package com.prep.user_ms.service.impl;

import com.prep.user_ms.dto.request.CreateUserRequest;
import com.prep.user_ms.dto.request.LoginRequest;
import com.prep.user_ms.dto.response.LoginResponse;
import com.prep.user_ms.dto.response.UserResponse;
import com.prep.user_ms.entity.User;
import com.prep.user_ms.exception.EmailAlreadyExistsException;
import com.prep.user_ms.mapper.UserMapper;
import com.prep.user_ms.repository.UserRepository;
import com.prep.user_ms.service.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;


    public UserServiceImpl(UserRepository repository,
                           UserMapper mapper,
                           BCryptPasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           JWTService jwtService
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public UserResponse register(CreateUserRequest request) {
        log.info("*=====> Registering user with email: {}", request.getEmail());

        if (repository.findByEmail(request.getEmail()).isPresent()) {
            log.warn("Registration failed. Email already exists: {}", request.getEmail());
            throw new EmailAlreadyExistsException("Email already exists");
        }
        User user = mapper.toEntity(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User SavedUser = repository.save(user);
        log.info("=====> User created successfully with id {}", SavedUser.getId());
        return mapper.toResponse(SavedUser);

    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        String token = jwtService.generateToken(auth.getName());

        return LoginResponse.builder()
//            .email(auth.getName())
//            .message("authentication successful")
                .accessToken(token)
                .tokenType("Bearer")
                .build();

    }


}