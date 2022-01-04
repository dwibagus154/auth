package com.dwibagus.auths.service;

import com.dwibagus.auths.model.User;
import com.dwibagus.auths.payload.UserResponse;
import com.dwibagus.auths.payload.UsernamePassword;

import java.util.List;

public interface AdminService {

    UserResponse createResponse(User user);
    UserResponse userActivate(Long id);
    List<UserResponse> getAllUser();

    UserResponse getUser(Long id);

    UserResponse deleteUser(Long id);

    UserResponse editUser(Long id, UsernamePassword usernamePassword);
}