package com.divyanshCode.BlogApplication.Controller;


import com.divyanshCode.BlogApplication.Util.JwtUtil;
import com.divyanshCode.BlogApplication.helper.JwtRequest;

import com.divyanshCode.BlogApplication.helper.JwtResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login (@RequestBody JwtRequest jwtRequest)
    {


       Authentication authentication = authenticationProvider.authenticate(
              new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(),jwtRequest.getPassword()));


       ///username islea nikal re h taaki token generate kr ske
       UserDetails userDetails = (UserDetails) authentication.getPrincipal();


       /// token ko generate kro.
        String token = jwtUtil.generateToken(userDetails);

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setToken(token);

        return ResponseEntity.ok().body(jwtResponse);

    }

}
