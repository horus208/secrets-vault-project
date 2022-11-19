package com.example.authenticationserver;

import com.example.authenticationserver.security.emailValidation.EmailValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class AuthenticationServerApplication {

    @Autowired
    EmailValidationService emailValidationService;
    public static void main(String[] args) {
        SpringApplication.run(AuthenticationServerApplication.class, args);
    }


@EventListener(ApplicationReadyEvent.class)
public void send()
{
    emailValidationService.sendValidationEmail("abdo22.ae56@gmail.com");
    System.out.println("send success");

}
}