package com.dwibagus.auths.service.impl;

import com.dwibagus.auths.model.User;
import com.dwibagus.auths.payload.LoginRequest;
import com.dwibagus.auths.payload.TokenResponse;
import com.dwibagus.auths.payload.UserResponse;
import com.dwibagus.auths.payload.UsernamePassword;
import com.dwibagus.auths.repository.UserRepository;
import com.dwibagus.auths.service.AuthService;
import com.dwibagus.auths.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Log4j2
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserResponse createResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setFullname(user.getFullname());
        userResponse.setPhone_number(user.getPhone_number());
        userResponse.setIsAdmin(user.getIsAdmin());
        userResponse.setActive(user.isActive());
        userResponse.setCreated_at(user.getCreated_at());
        userResponse.setUpdated_at(user.getUpdated_at());
        return userResponse;
    }

    @Override
    public UserResponse register(UsernamePassword req) {
        User isUser = userRepository.getDistinctTopByUsername(req.getUsername());
        if (isUser != null){
            return null;
        }
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setEmail(req.getEmail());
        user.setIsAdmin(req.getIsAdmin());
        userRepository.save(user);

//        create user response
        UserResponse userResponse = this.createResponse(user);
        return userResponse;
    }



    @Override
    public TokenResponse loginMember(LoginRequest req){
        User user = userRepository.getDistinctTopByUsername(req.getUsername());
        Boolean isMatch = passwordEncoder.matches(req.getPassword(), user.getPassword());
        TokenResponse tokenResponse = new TokenResponse();
        if (isMatch){
            if (user.isActive()){
                tokenResponse.setToken(jwtUtil.generateToken(req.getUsername()));
            }else{
                tokenResponse.setToken(null);
            }
            return tokenResponse;
        }else{
            return null;
        }
    }
}
