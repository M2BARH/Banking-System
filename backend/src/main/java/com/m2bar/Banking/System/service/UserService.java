package com.m2bar.Banking.System.service;

import com.m2bar.Banking.System.models.EmailRequest;
import com.m2bar.Banking.System.models.LoginReq;
import com.m2bar.Banking.System.models.StringResponse;
import com.m2bar.Banking.System.models.UserDto;

public interface UserService {

    StringResponse signIn(LoginReq loginReq);

    StringResponse signUp(UserDto userDto);

    StringResponse resetPassword(EmailRequest emailRequest);
}
