package com.dwibagus.auths.controller;

import com.dwibagus.auths.kafka.KafkaConsumer;
import com.dwibagus.auths.kafka.KafkaProducer;
import com.dwibagus.auths.payload.LoginRequest;
import com.dwibagus.auths.payload.UserResponse;
import com.dwibagus.auths.payload.UsernamePassword;
import com.dwibagus.auths.response.CommonResponseGenerator;
import com.dwibagus.auths.service.AdminService;
import com.dwibagus.auths.service.AuthService;
import com.dwibagus.auths.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthService authService;
    private final AdminService adminService;

    @Autowired
    CommonResponseGenerator commonResponseGenerator;

    @Autowired
    private JwtUtil jwtUtil;

    //    kafka
    @Autowired
    private KafkaConsumer consumer;

    @Autowired
    private KafkaProducer producer;

    @PostMapping("/login")
    public ResponseEntity<?> loginMember(@RequestBody LoginRequest req) {
        try {
            if (authService.loginMember(req) == null){
                return new ResponseEntity<>(commonResponseGenerator.response(null, "Password didnt match" ,"403"),HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok(commonResponseGenerator.response(authService.loginMember(req), "success login", "200"));
        }catch (Exception e){
            return new ResponseEntity<>(commonResponseGenerator.response(null, e.getMessage() ,"403"),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsernamePassword req) {
        try{
            UserResponse userResponse = authService.register(req);
            if (userResponse == null){
                return new ResponseEntity<>(commonResponseGenerator.response(null, "User Already Exist","403"),HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok(commonResponseGenerator.response(userResponse, "success register", "200"));
        }catch (Exception e){
            return new ResponseEntity<>(commonResponseGenerator.response(null, e.getMessage() ,"403"),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/user/activate/{id}")
    public ResponseEntity<?> userActivated(@PathVariable Long id) {
        try {
            UserResponse userResponse = adminService.userActivate(id);
            return ResponseEntity.ok(commonResponseGenerator.response(userResponse, "user activated", "200"));
        }catch (Exception e){
            return new ResponseEntity<>(commonResponseGenerator.response(null, e.getMessage() ,"403"),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllUser(){
        try {
            return ResponseEntity.ok(commonResponseGenerator.response(adminService.getAllUser(), "get all user success", "200"));
        }catch (Exception e){
            return new ResponseEntity<>(commonResponseGenerator.response(null, "there is no user", "400"),HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        System.out.println(id);
        try{
            UserResponse userResponse =  adminService.getUser(id);
            return ResponseEntity.ok(commonResponseGenerator.response(userResponse, "get user success", "200"));
        }catch (Exception e){
            return new ResponseEntity<>(commonResponseGenerator.response(null, "there is no user with id " + id, "400"),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> editUser(@PathVariable Long id, @RequestBody UsernamePassword req) {
        try {
            UserResponse userResponse = adminService.editUser(id, req);
            return ResponseEntity.ok(commonResponseGenerator.response(userResponse, "edit user success", "200"));
        }catch (Exception e){
            return new ResponseEntity<>(commonResponseGenerator.response(null, e.getMessage() ,"403"),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        System.out.println(id);
        try{
            UserResponse userResponse =  adminService.deleteUser(id);
            return ResponseEntity.ok(commonResponseGenerator.response(userResponse, "delete success", "200"));
        }catch (Exception e){
            return new ResponseEntity<>(commonResponseGenerator.response(null, "there is no user with id " + id, "400"),HttpStatus.BAD_REQUEST);
        }
    }




    @GetMapping("/vo/user/{id}")
    public ResponseEntity<?> getUserVO(@PathVariable Long id){
        System.out.println(id);
        try{
            UserResponse userResponse =  adminService.getUser(id);
            return ResponseEntity.ok(userResponse);
        }catch (Exception e){
            return new ResponseEntity<>(commonResponseGenerator.response(null, "there is no user with id " + id, "400"),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/send")
    public void send(@RequestBody String data) {
        producer.produce(data);
    }

    @GetMapping("/receive")
    public List<String> receive() {
        return KafkaConsumer.messages;
    }

    public KafkaConsumer getConsumer() {
        return consumer;
    }

    public void setConsumer(KafkaConsumer consumer) {
        this.consumer = consumer;
    }

    public KafkaProducer getProducer() {
        return producer;
    }

    public void setProducer(KafkaProducer producer) {
        this.producer = producer;
    }

}
