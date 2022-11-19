package com.example.authenticationserver.security;

import com.example.authenticationserver.exceptions.PasswordEmailAuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Component;

@Component
public class EmailPasswordAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {



            UserDetails user = userDetailsService.loadUserByUsername(authentication.getName());
            boolean passwordMatches = passwordEncoder.matches((String)authentication.getCredentials(), user.getPassword());
            if(!passwordMatches)
                throw new PasswordEmailAuthenticationException("wrong password or email address");

            return new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(), user.getAuthorities());





    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
