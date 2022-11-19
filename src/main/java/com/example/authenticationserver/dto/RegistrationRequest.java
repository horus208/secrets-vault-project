package com.example.authenticationserver.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.Mapping;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class RegistrationRequest
{
    @NotEmpty(message = "first name shouldn't be null") @Length(min = 3,max = 20)
    private String firstName;
    @NotEmpty(message = "second name shouldn't be null") @Length(min = 3,max = 20)
    private String secondName;
    @NotEmpty(message = "email shouldn't be null")
    @Email(message = "email should be valid email address")
    private String email;
    @NotEmpty(message = "password shouldn't be null")
    @Length(min = 10,max = 30)
    private String password;

    public RegistrationRequest(String firstName, String secondName, String email, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
    }
}
