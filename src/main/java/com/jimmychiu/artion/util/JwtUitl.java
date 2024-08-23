package com.jimmychiu.artion.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUitl {
    private static final String secretKey = "Jimmy";

    public static String generateToken(Map<String, Object> claims){
        return JWT.create()
                .withClaim("claims", claims)//添加載荷
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))//添加過期時間
                .sign(Algorithm.HMAC256(secretKey));
    }

    public static Map<String, Object> parseToken(String token) {
        // 去掉 "Bearer " 前缀
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        return JWT.require(Algorithm.HMAC256(secretKey))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();

    }
}
