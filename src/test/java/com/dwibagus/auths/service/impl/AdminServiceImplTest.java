package com.dwibagus.auths.service.impl;

import com.dwibagus.auths.model.User;
import com.dwibagus.auths.payload.UserResponse;
import com.dwibagus.auths.repository.UserRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class AdminServiceImplTest {
    private final EasyRandom EASY_RANDOM = new EasyRandom();
    private Long id;

    @InjectMocks
    private AdminServiceImpl service;
    @Mock
    private UserRepository userRepository;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        id = EASY_RANDOM.nextObject(Long.class);
    }

    @Test
    public void getOne_WillReturnProductOutput() {
        // Given
        UserResponse userResponse = EASY_RANDOM.nextObject(UserResponse.class);
        User user = new User();
        user.setId(userResponse.getId());
        user.setIsAdmin(userResponse.getIsAdmin());
        user.setFullname(userResponse.getFullname());
        user.setUsername(userResponse.getUsername());
        user.setPhone_number(userResponse.getPhone_number());
        user.setEmail(userResponse.getEmail());
        user.setCreated_at(userResponse.getCreated_at());
        user.setUpdated_at(userResponse.getUpdated_at());
        user.setActive(true);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        // When
        var result = service.getUser(user.getId());

        // Then
        verify(userRepository, times(1)).findById(user.getId());
        assertEquals(userResponse, result);
    }

}