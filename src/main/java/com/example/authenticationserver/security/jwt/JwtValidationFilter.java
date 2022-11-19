package com.example.authenticationserver.security.jwt;

import com.example.authenticationserver.exceptions.ErrorDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Service
public class JwtValidationFilter extends OncePerRequestFilter {

    @Autowired
    @Qualifier(JwtProviderBeans.jwtAuthentication)
    private JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String jwtToken = request.getHeader("Authorization");
        try
        {
            jwtProvider.validate(jwtToken);
            filterChain.doFilter(request,response);
        }
        catch (Exception e)
        {
            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.setTitle("authorization failed");
            errorDetails.setStatus(HttpStatus.UNAUTHORIZED.value());
            errorDetails.setTimeStamp(new Date().getTime());
            errorDetails.setDetail(e.getMessage());

            ObjectMapper mapper = new ObjectMapper();
            String body = mapper.writeValueAsString(errorDetails);

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().print(body);
            return;

        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/login")||request.getServletPath().equals("/register");
    }
}