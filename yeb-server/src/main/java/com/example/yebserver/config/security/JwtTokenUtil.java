package com.example.yebserver.config.security;


import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
//@ConfigurationProperties(prefix = "jwt")
public class JwtTokenUtil {
    private static String CLAIM_KEY_USERNAME="sub";
    private static String CLAIM_KEY_CREATED="created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(UserDetails userDetails){
        HashMap<String, Object> map = new HashMap<>();
        map.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        map.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(map);
    }

    public String generateToken(Map<String,Object> map){
        return Jwts.builder()
                .setClaims(map)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    public Date generateExpirationDate() {
        return new Date(System.currentTimeMillis()+expiration*1000);
    }
    public String getUsernameFromToken(String token){
        String username;
        try {
            Claims claims=getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username=null;
        }
        return username;
    }

    public Claims getClaimsFromToken(String token) {

        Claims claims=null;
        try {
            claims = Jwts.parser()
                   .setSigningKey(secret)
                   .parseClaimsJws(token)
                   .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }
    public Boolean validateToken(String token,UserDetails userDetails){
        Claims claims = getClaimsFromToken(token);
        return getUsernameFromToken(token).equals(userDetails.getUsername()) && !claims.getExpiration().before(new Date());
    }


}
