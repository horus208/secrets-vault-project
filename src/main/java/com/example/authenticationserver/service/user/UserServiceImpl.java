package com.example.authenticationserver.service.user;

import com.example.authenticationserver.dto.RegistrationRequest;
import com.example.authenticationserver.exceptions.DuplicateEntryException;
import com.example.authenticationserver.exceptions.ResourceNotFoundException;
import com.example.authenticationserver.mappers.DtoMapper;
import com.example.authenticationserver.model.AppUser;
import com.example.authenticationserver.persistance.UserRepo;

import com.example.authenticationserver.security.emailValidation.EmailValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    UserRepo userRepo;
    @Autowired
    DtoMapper<AppUser,RegistrationRequest> dtoMapper;

    @Autowired
    EmailValidationService emailValidationService;
    @Override
    public void createUser(RegistrationRequest memberInfo)
    {

        AppUser member = dtoMapper.mapDto(memberInfo);

        try {
            userRepo.save(member);
            emailValidationService.sendValidationEmail(memberInfo.getEmail());
        }
        catch ( DuplicateKeyException exception)
        {
            throw new DuplicateEntryException("this email address already exists");
        }
        catch (Exception exception)
        {
            System.out.println("what the hell is going on");
            System.out.println(exception.getCause());
        }


    }

    @Override
    public AppUser getUser(String email) {
        Optional<AppUser> user = userRepo.findByEmail(email);
        if(user.isEmpty())
            throw new ResourceNotFoundException("user with email = "+email+" does not exist" );

        return user.get();
    }

}
