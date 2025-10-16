package com.lndev.codeshut.service;

import com.lndev.codeshut.dto.LoginDto;
import com.lndev.codeshut.dto.SignUpDto;
import com.lndev.codeshut.dto.UserDto;
import com.lndev.codeshut.entities.UserEntity;
import com.lndev.codeshut.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserDto signUp(SignUpDto signUpDto) {
        Optional<UserEntity> user = userRepository.findByEmail(signUpDto.getEmail());
        if(user.isPresent()) {
            log.error("User with email {} already exists", signUpDto.getEmail());
            throw new BadCredentialsException("User already exists");
        }
        UserEntity userEntity = modelMapper.map(signUpDto, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        userEntity = userRepository.save(userEntity);
        return modelMapper.map(userEntity, UserDto.class);
    }

    public String login(LoginDto loginDto) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        return jwtService.createToken(loginDto.getEmail());
    }
}
