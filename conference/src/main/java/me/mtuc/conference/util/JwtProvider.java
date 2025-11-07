package me.mtuc.conference.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8))).build().parseClaimsJws(token);

            String subject = this.getSubjetFromToken(token);
            Util util = new Util(new ObjectMapper());

            // todo: token valid확인하는 과정이 필요함
            // todo: test위해서 valid true
            JsonNode jsonNode = util.stringToJson(subject);

            if(true){
                return true;
            }
        } catch (ExpiredJwtException e){
            return false;
        } catch (SignatureException e){
            return false;
        } catch (JsonProcessingException e){
            return false;
        }
        return true;
    }
}

