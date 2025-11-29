package com.example.Restdemo;

import com.example.Restdemo.Mapper.UserMapper;
import com.example.Restdemo.dto.UserRequest;
import com.example.Restdemo.dto.UserResponse;
import com.example.Restdemo.model.User;
import com.example.Restdemo.repository.UserRepository;
import com.example.Restdemo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceCreateTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private UserRequest userRequest;
    private User user;
    private UserResponse userResponse;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        userRequest = new UserRequest("Johnj", "john@gmail.com", "ADMIN");

        user = new User();
        user.setId(1L);
        user.setName("John");
        user.setEmail("john@gmail.com");
        user.setRole("ADMIN");

        userResponse = new UserResponse(1L, "John", "john@gmail.com", "ADMIN");
    }

    @Test
    void testCreateUser() {

        // 1. Mock mapper converting request -> entity
        when(userMapper.toEntity(userRequest)).thenReturn(user);

        // 2. Mock repository saving user
        when(userRepository.save(user)).thenReturn(user);

        // 3. Mock mapper converting entity -> response
        when(userMapper.toResponse(user)).thenReturn(userResponse);

        // 4. Call service method
        UserResponse response = userService.create(userRequest);

        // 5. Validate the result
        assertNotNull(response);
        assertEquals("John", response.getName());
        assertEquals("john@gmail.com", response.getEmail());

        // 6. Verify repository was called once
        verify(userRepository, times(1)).save(user);
    }
}
