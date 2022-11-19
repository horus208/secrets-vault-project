package com.example.authenticationserver.mappers;

import com.example.authenticationserver.dto.RegistrationRequest;
import com.example.authenticationserver.model.AppUser;
import com.example.authenticationserver.model.MemberProfile;
import com.example.authenticationserver.model.Role;
import com.example.authenticationserver.model.Secret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserMapper implements DtoMapper<AppUser,RegistrationRequest> {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public AppUser mapDto(RegistrationRequest memberInfo)
    {



        MemberProfile member = new MemberProfile();
        member.setFirstName(memberInfo.getFirstName());
        member.setSecondName(memberInfo.getSecondName());
        member.setSecrets(new ArrayList<Secret>());

        AppUser user  = new AppUser();
        user.setEmail(memberInfo.getEmail());
        user.setPwd(passwordEncoder.encode(memberInfo.getPassword()));
        user.setRole(Role.USER);
        user.setVerified(false);
        user.setProfile(member);


        return user;
    }


}
