package org.javapatil.studproject.util.security.admin.impl;

import java.util.Date;

import javax.crypto.SecretKey;

import org.javapatil.studproject.util.security.admin.SessionHandler;
import org.javapatil.studproject.util.security.admin.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Service
public class SessionHandlerImpl implements SessionHandler {

    private SecretKey secretKey = MacProvider.generateKey();

    @Override
    public String createSession(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUserName());
       
    	claims.put("userName", user.getUserName());
    	claims.put("organizationId", user.getOrganizationId());
    	claims.put("organizationName", user.getOrganizationName());
    	claims.put("pass", user.getPass());
    	claims.put("status", user.getStatus());
    	claims.put("emailId", user.getEmailId());
    	claims.put("mobileNo", user.getMobileNo());
    	claims.put("roleName", user.getRoleName());
                
        Date date = new Date();
        Date expireAt = new Date(System.currentTimeMillis() + 60*60*1000);
        String jwt = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(expireAt)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return jwt;
    }

    @Override
    public User validateToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            User user = new User(claims.get("userName").toString(), Integer.parseInt(claims.get("organizationId").toString()),claims.get("organizationName").toString(),claims.get("pass").toString(),claims.get("status").toString(),claims.get("emailId").toString(),claims.get("mobileNo").toString(),claims.get("roleName").toString());
            return user;
        } catch (Exception e) {

        }
        
        return null;
    }
}
