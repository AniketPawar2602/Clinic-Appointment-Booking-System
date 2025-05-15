package com.example.Clinic_Appoitment_App.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY_STRING = "3dfb4aa3e8249199471cb09bad9c7435821fcd70dee680770bb111ba62f5b51a";

    private final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isValidToken(String token) {

        try{
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);

            return true;
        }
        catch (ExpiredJwtException e){
            System.out.println("Token expired: "+e.getMessage());
        }
        catch (Exception e){
            System.out.println("Token Error: "+e.getMessage());
        }
        return false;
    }
}
