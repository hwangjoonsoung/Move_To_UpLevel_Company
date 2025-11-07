package me.mtuc.conference.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Value;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.lang.model.element.NestingKind;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@Getter
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;

    private final long expirationSecond = 1000 * 60 * 30;

    public String generateRefreshTokenByEmail(String email, String password) {
        Date now = new Date();
        Date expairyDate = new Date(now.getTime() + expirationSecond);
        String subject = "";
        JwtBuilder jwtBuilder = Jwts.builder().setSubject(subject).setIssuedAt(now).setExpiration(expairyDate).signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)));
        return jwtBuilder.compact();
    }

    public String generateRefreshTokenById(String id) {
        Date now = new Date();
        Date expairyDate = new Date(now.getTime() + expirationSecond);

        String subject = "";
        JwtBuilder jwtBuilder = Jwts.builder().setSubject(subject).setIssuedAt(now).setExpiration(expairyDate).signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)));
        return jwtBuilder.compact();
    }

    public String getSubjetFromToken(String token) {
        String subject = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8))).build().parseClaimsJws(token).getBody().getSubject();
        return subject;
    }

    public boolean validateToken(String token) {
        try {
            // todo: 이 작업을 왜 하는거지?
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8))).build().parseClaimsJws(token);

            String subject = this.getSubjetFromToken(token);

        } catch (Exception e) {

        }

    }
}

