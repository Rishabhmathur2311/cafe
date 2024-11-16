package com.hotel_management.hotel_management.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtUtil {
    private static final String SECRET_KEY_BASE64 = "671491AE98362741F722202EED3288E8FF2508B35315ADBF75EEB3195A926B40";
    private String doGenerateToken(Map<String, Object> extraClaims, UserDetails details) throws Exception {
        String token= Jwts.builder().
                setClaims(extraClaims).
                setSubject(details.getUsername()).
                setIssuedAt(new Date(System.currentTimeMillis() + 1000*60*60*24)).
                signWith(SignatureAlgorithm.HS256, getSigningKey()).compact();
        System.out.println(token);
        System.out.println(token);
        return token;
    }

    public String generateToken(UserDetails details) throws Exception {
        Map<String, Object> extraClaims = new HashMap<>();
        System.out.println("Hello");
        return doGenerateToken(extraClaims, details);
    }

    public boolean validateToken(String token, UserDetails details) throws Exception {
        final String userName=extractUserName(token);

        return (userName.equals(details.getUsername()) && !isTokenExpired(token));
    }

    public String extractUserName(String token) throws Exception {
        System.out.println(token);
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) throws Exception {

        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) throws Exception {
        return extractExpiration(token).before(new Date());
    }

    private Claims extractAllClaims(String token) throws Exception {
        Claims claims= Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(token).getBody();
        System.out.println("claims");
        System.out.println(claims);
        return claims;
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws Exception {
        return claimsResolver.apply(extractAllClaims(token));

    }

    private SecretKey getSigningKey() throws Exception {
        byte[] decodedKeyBytes = Decoders.BASE64.decode("413F4428472B4B62506553685660597033733676397924422645294840406351");
        System.out.println(decodedKeyBytes);

        SecretKey k=Keys.secretKeyFor(SignatureAlgorithm.HS256);
        return k;

    }
}