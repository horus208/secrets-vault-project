package com.example.authenticationserver.service.user;

import com.example.authenticationserver.dto.RegistrationRequest;
import com.example.authenticationserver.model.AppUser;

public interface UserService
{
    void createUser(RegistrationRequest userInfo);
     AppUser getUser(String email);
}
