package com.m2bar.Banking.System.controller;

import com.m2bar.Banking.System.api.UsersApi;
import com.m2bar.Banking.System.models.EmailRequest;
import com.m2bar.Banking.System.models.LoginReq;
import com.m2bar.Banking.System.models.StringResponse;
import com.m2bar.Banking.System.models.UserDto;
import com.m2bar.Banking.System.service.EmailService;
import com.m2bar.Banking.System.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController implements UsersApi {

    private final UserService userService;

    private final EmailService emailService;

    public UserController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @Override
    public ResponseEntity<StringResponse> signIn(LoginReq body) {
        return new ResponseEntity<>(userService.signIn(body), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StringResponse> signUp(UserDto body) {
        return new ResponseEntity<>(userService.signUp(body), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<StringResponse> resetPassword(EmailRequest body) {
        return new ResponseEntity<>(userService.resetPassword(body), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StringResponse> sendVerificationCode(EmailRequest body) {
        return new ResponseEntity<>(emailService.sendSimpleMessage(body), HttpStatus.OK);
    }
}
