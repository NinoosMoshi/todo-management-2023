package com.ninos.service.impl;

import com.ninos.dto.LoginDTO;
import com.ninos.dto.RegisterDTO;
import com.ninos.entity.Role;
import com.ninos.entity.User;
import com.ninos.exception.TodoApiException;
import com.ninos.repositroy.RoleRepository;
import com.ninos.repositroy.UserRepository;
import com.ninos.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public String register(RegisterDTO registerDTO) {

        // check if username is exists in database
        if(userRepository.existsByUsername(registerDTO.getUsername())){
            throw new TodoApiException(HttpStatus.BAD_REQUEST, "Username is already exists");
        }

        // check if email is exists in database
        if(userRepository.existsByEmail(registerDTO.getEmail())){
            throw new TodoApiException(HttpStatus.BAD_REQUEST, "Email is already exists");
        }

        User user = new User();
        user.setName(registerDTO.getName());
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);

        user.setRoles(roles);

        userRepository.save(user);

        return "User Registered Successfully";
    }

    @Override
    public String login(LoginDTO loginDTO) {

       Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "USer logged-in successfully";
    }
}
