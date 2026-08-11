package com.prep.user_ms.service.impl;

import com.prep.user_ms.dto.request.CreateUserRequest;
import com.prep.user_ms.dto.response.UserResponse;
import com.prep.user_ms.entity.User;
import com.prep.user_ms.exception.EmailAlreadyExistsException;
import com.prep.user_ms.mapper.UserMapper;
import com.prep.user_ms.repository.UserRepository;
import com.prep.user_ms.service.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);


    public UserServiceImpl(UserRepository repository,
                           UserMapper mapper  , BCryptPasswordEncoder passwordEncoder
                           ) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse register(CreateUserRequest request) {
        log.info("*=====> Registering user with email: {}", request.getEmail());

        if (repository.findByEmail(request.getEmail()).isPresent()) {
            log.warn("Registration failed. Email already exists: {}", request.getEmail());
            throw new EmailAlreadyExistsException("Email already exists");
        }
        User user = mapper.toEntity(request);

//        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User SavedUser = repository.save(user);
        log.info("=====> User created successfully with id {}", SavedUser.getId());
        return mapper.toResponse(SavedUser);

    }
}