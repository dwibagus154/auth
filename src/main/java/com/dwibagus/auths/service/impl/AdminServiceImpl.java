package com.dwibagus.auths.service.impl;

import com.dwibagus.auths.kafka.KafkaConsumer;
import com.dwibagus.auths.kafka.KafkaProducer;
import com.dwibagus.auths.model.User;
import com.dwibagus.auths.payload.UserResponse;
import com.dwibagus.auths.payload.UsernamePassword;
import com.dwibagus.auths.repository.UserRepository;
import com.dwibagus.auths.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    @Autowired
    private KafkaConsumer consumer;

    @Autowired
    private KafkaProducer producer;

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
    public UserResponse userActivate(Long id){
        User user = userRepository.findById(id).get();
        if(user.isActive()){
            return null;
        }
        user.setActive(true);
        userRepository.save(user);

//        create user response
        UserResponse userResponse = this.createResponse(user);
        return userResponse;
    }

    @Override
    public List<UserResponse> getAllUser(){
        List<User> userList = userRepository.findAll();
        List<UserResponse> userResponseList = new ArrayList<>();
        UserResponse userResponse = new UserResponse();
        for (int i = 0; i < userList.size(); i++){
            userResponse = this.createResponse(userList.get(i));
            userResponseList.add(userResponse);
        }
        return userResponseList;
    }

    @Override
    public UserResponse getUser(Long id){
        User user = userRepository.findById(id).get();
        //        create user response
        UserResponse userResponse = this.createResponse(user);
        return userResponse;
    }

    @Override
    public UserResponse deleteUser(Long id){
        User user = userRepository.findById(id).get();
        System.out.println(user.getUsername());

        //      send kafka
        System.out.println(String.valueOf(id));
        producer.produce(String.valueOf(id));

        //        create user response
        UserResponse userResponse = this.createResponse(user);
        userRepository.deleteById(id);
        return userResponse;
    }

    @Override
    public UserResponse editUser(Long id, UsernamePassword usernamePassword){
        User user = userRepository.findById(id).get();
        System.out.println(user.getUsername());
        if (usernamePassword.getUsername() != null){
            user.setUsername(usernamePassword.getUsername());
        }
        if (usernamePassword.getEmail() != null){
            user.setEmail(usernamePassword.getEmail());
        }
        if (usernamePassword.getFullname()!= null){
            user.setFullname(usernamePassword.getFullname());
        }
        if (usernamePassword.getPhone_number() != null){
            user.setPhone_number(usernamePassword.getPhone_number());
        }
        user.setUpdated_at(new Date());
        userRepository.save(user);
        //        create user response
        UserResponse userResponse = this.createResponse(user);
        return userResponse;
    }

}