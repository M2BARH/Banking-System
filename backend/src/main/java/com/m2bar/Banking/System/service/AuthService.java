package com.m2bar.Banking.System.service;

import com.m2bar.Banking.System.models.UserDto;

public interface AuthService {

    String generateToken(UserDto dto);
}
