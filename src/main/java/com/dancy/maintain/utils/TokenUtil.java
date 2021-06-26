package com.dancy.maintain.utils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @program: bridge_maintain_backend
 * @description: 实现token的创建验证
 * @author: Bangyang Shan
 * @create: 2021-06-21 11:33
 **/
public class TokenUtil {
    private static final String signature = "Shine";

    public static String getToken(Map<String, Object> payload) {
        JwtBuilder builder = Jwts.builder();
        long time = 1000 * 60 * 60 * 24;

        return builder.setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setClaims(payload)
                .setSubject("login")
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256, signature)
                .compact();
    }

    public static void parseToken(String token) {
        Jwts.parser().setSigningKey(signature).parseClaimsJws(token).getBody();
    }
}
