package com.m2bar.Banking.System.service.impl;

import com.m2bar.Banking.System.models.UserDto;
import com.m2bar.Banking.System.service.AuthService;
import com.m2bar.Banking.System.util.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;

    public AuthServiceImpl(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String generateToken(UserDto dto) {
        String userId = dto.getUsername();
        return jwtUtil.generateToken(userId);
    }
}
