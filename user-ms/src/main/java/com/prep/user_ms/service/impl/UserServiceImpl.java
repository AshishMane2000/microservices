package com.prep.user_ms.service.impl;

import com.prep.user_ms.dto.request.CreateUserRequest;
import com.prep.user_ms.dto.response.UserResponse;
import com.prep.user_ms.entity.User;
import com.prep.user_ms.exception.EmailAlreadyExistsException;
import com.prep.user_ms.mapper.UserMapper;
import com.prep.user_ms.repository.UserRepository;
import com.prep.user_ms.service.interfaces.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository repository,
                           UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserResponse register(CreateUserRequest request){
    if(repository.findByEmail(request.getEmail()).isPresent()){
        throw new EmailAlreadyExistsException("Email already exists");
    }
    mapper.toEntity(request);

   User SavedUser =  repository.save(mapper.toEntity(request));

    return mapper.toResponse(SavedUser);

    }
}