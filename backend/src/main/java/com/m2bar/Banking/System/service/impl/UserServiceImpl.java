package com.m2bar.Banking.System.service.impl;

import com.m2bar.Banking.System.mapper.UserMapper;
import com.m2bar.Banking.System.models.EmailRequest;
import com.m2bar.Banking.System.models.LoginReq;
import com.m2bar.Banking.System.models.StringResponse;
import com.m2bar.Banking.System.models.UserDto;
import com.m2bar.Banking.System.repository.CustomerRepository;
import com.m2bar.Banking.System.repository.UserRepository;
import com.m2bar.Banking.System.service.AuthService;
import com.m2bar.Banking.System.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final AuthService authService;

    private final UserRepository userRepository;

    private final CustomerRepository customerRepository;

    public UserServiceImpl(AuthService authService, UserRepository userRepository, CustomerRepository customerRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public StringResponse signIn(LoginReq loginReq) {
        try {
            StringResponse response = new StringResponse();
            if (loginReq.getUsername() == null || loginReq.getUsername().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid username format");
            }
            if (loginReq.getPassword() == null || loginReq.getPassword().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid password format");
            }
            String username = loginReq.getUsername();
            UserDto dto = UserMapper.CUSTOMER_MAPPER.entityToDto(userRepository.findByUsername(username));
            if (dto == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with username '" + username + "' was found!!");
            }
            if (dto.getUsername() != null && dto.getPassword() != null) {
                String rawPassword = loginReq.getPassword();
                String hashedPasswordFromDto = dto.getPassword();
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                if (rawPassword != null && hashedPasswordFromDto != null) {
                    if (!passwordEncoder.matches(rawPassword, hashedPasswordFromDto)) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is incorrect!");
                    }
                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid username or password!");
                }
            }
            response.setToken(authService.generateToken(dto));
            response.setSuccessMessage("Signed in successfully");
            return response;
        } catch (ResponseStatusException e) {
            StringResponse response = new StringResponse();
            response.setErrorMessage(e.getReason());
            return response;
        }
    }

    @Override
    public StringResponse signUp(UserDto userDto) {
        try {
            StringResponse response = new StringResponse();
            if (userDto != null) {
                if (userDto.getIdentityNumber().length() != 13) {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "Identity Number must be 13 digits"
                    );
                }

                if (userRepository.findByIdentityNumber(userDto.getIdentityNumber()) != null) {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND, ("User with id '" + userDto.getIdentityNumber() + "' already exist")
                    );
                }

                if (customerRepository.findByIdentityNumber(userDto.getIdentityNumber()) == null) {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND, ("Customer with id '" + userDto.getIdentityNumber() + "' was not found...")
                    );
                }

                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                userDto.setPassword(encoder.encode(userDto.getPassword()));
                userRepository.save(UserMapper.CUSTOMER_MAPPER.dtoToEntity(
                        userDto
                ));
            } else {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "User object is null"
                );
            }

            response.setSuccessMessage("Signed up successfully");
            return response;
        } catch (ResponseStatusException e) {
            StringResponse response = new StringResponse();
            response.setErrorMessage(e.getReason());
            return response;
        }
    }

    @Override
    public StringResponse resetPassword(EmailRequest emailRequest) {
        try {
            String email = emailRequest.getEmail();

            UserDto userDto = UserMapper.CUSTOMER_MAPPER.entityToDto(
                    userRepository.findByEmailAddress(email)
            );

            if (userDto == null) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, ("No user with email '" + email + "' was found")
                );
            }
            String newPassword = emailRequest.getPassword();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            userDto.setPassword(encoder.encode(newPassword));

            userRepository.saveAndFlush(
                    UserMapper.CUSTOMER_MAPPER.dtoToEntity(userDto)
            );

            StringResponse response = new StringResponse();
            response.setSuccessMessage("Password successfully reset");
            return response;
        } catch (ResponseStatusException e) {
            StringResponse response = new StringResponse();
            response.setErrorMessage(e.getReason());
            return response;
        }
    }
}
