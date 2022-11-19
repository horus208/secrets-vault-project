package com.example.authenticationserver.security;

import com.example.authenticationserver.exceptions.ErrorDetails;
import com.example.authenticationserver.security.jwt.JwtProvider;
import com.example.authenticationserver.security.jwt.JwtProviderBeans;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class EmailPasswordAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    @Qualifier(JwtProviderBeans.jwtAuthentication)
    private JwtProvider jwtProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
        {
            String email = request.getHeader("email");
            String password = request.getHeader("password");
            if(email==null||email.equals("") || password == null ||password.equals(""))
            {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                return;
            }

            try
            {

                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
                String jwt = jwtProvider.generateToken(email);
                response.setHeader("Authorization",jwt);
                return;



            }
            catch (AuthenticationException exception)
            {
                ErrorDetails errorDetails = new ErrorDetails();
                errorDetails.setTitle("Authentication failed");
                errorDetails.setStatus(HttpStatus.UNAUTHORIZED.value());
                errorDetails.setTimeStamp(new Date().getTime());
                errorDetails.setDetail(exception.getMessage());

                ObjectMapper mapper = new ObjectMapper();
                String body = mapper.writeValueAsString(errorDetails);

                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType("application/json");
                response.getWriter().print(body);
                return;
            }




}

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException
    {
        return !request.getServletPath().equals("/login");
    }
}
