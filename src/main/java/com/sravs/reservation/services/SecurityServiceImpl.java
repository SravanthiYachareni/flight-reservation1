package com.sravs.reservation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService{
    @Autowired
    UserDetailsService service;

    AuthenticationManager authenticationManager;
    @Override
    public boolean login(String username, String password) {
        UserDetails userDetails =service.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(userDetails,password
        ,userDetails.getAuthorities());
        boolean result = token.isAuthenticated();
        if(result){
            SecurityContextHolder .getContext().setAuthentication(token);
        }
        return result;
    }
}
