package com.divyanshCode.BlogApplication.Util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;



@Service
public class JwtUtil {


    private static final String secret_key = "mysecretkeymysecretkeymysecretkey";


    ///this is the code to generate token
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
                .signWith(Keys.hmacShaKeyFor(secret_key.getBytes()), SignatureAlgorithm.HS256)
                .compact();///compact means covert all the data into string.
    }


    /// yha per hum email(username) extract krre h token se
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret_key.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

   /// this will tell whether the token is expired or not from the token
    public boolean isTokenExpired(String token)
    {
       Date expiration = Jwts.parserBuilder()
               .setSigningKey(Keys.hmacShaKeyFor(secret_key.getBytes()))
               .build()
               .parseClaimsJws(token)
               .getBody()
               .getExpiration();

       return expiration.before(new Date());
    }


    public boolean validateToken(String token, UserDetails userDetails)
    {
        String username = extractUsername(token);

        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}


