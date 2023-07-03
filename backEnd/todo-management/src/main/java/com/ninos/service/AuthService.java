package com.ninos.service;

import com.ninos.dto.LoginDTO;
import com.ninos.dto.RegisterDTO;

public interface AuthService {

    String register(RegisterDTO registerDTO);
    String login(LoginDTO loginDTO);

}
