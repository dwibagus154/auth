package com.dwibagus.auths.service;

import com.dwibagus.auths.model.User;
import com.dwibagus.auths.payload.LoginRequest;
import com.dwibagus.auths.payload.TokenResponse;
import com.dwibagus.auths.payload.UserResponse;
import com.dwibagus.auths.payload.UsernamePassword;

public interface AuthService {
    UserResponse createResponse(User user);
    UserResponse register(UsernamePassword req);

    TokenResponse loginMember(LoginRequest req);
}
