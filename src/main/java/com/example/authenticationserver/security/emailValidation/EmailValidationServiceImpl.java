package com.example.authenticationserver.security.emailValidation;

import com.example.authenticationserver.security.jwt.JwtProvider;
import com.example.authenticationserver.security.jwt.JwtProviderBeans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailValidationServiceImpl implements EmailValidationService {


   private JavaMailSender mailSender = new JavaMailSenderImpl();
   @Autowired
   @Qualifier(JwtProviderBeans.jwtEmailValidation)
   private JwtProvider jwtProvider;


    @Override
    public void sendValidationEmail(String emailAddress)
    {
        String token = jwtProvider.generateToken(emailAddress);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailAddress);
        message.setFrom("abdoo2083001@gmail.com");
        message.setSubject("Email Address Confirmation");
        message.setText("thanks for joining us, here is the key to confirm your email address \n"+token);
        mailSender.send(message);
    }
}
