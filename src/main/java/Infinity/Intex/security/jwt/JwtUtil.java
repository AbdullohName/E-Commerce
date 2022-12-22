package Infinity.Intex.security.jwt;

import Infinity.Intex.utils.MyDateUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Date;
@RequiredArgsConstructor
@Component
public class JwtUtil {
    private final Environment environment;

    @Value("${spring.security.key}")
    private String key;

    public String generateToken(String userId){
        return Jwts.builder()
                .claim("sub", userId)
                .claim("exp", MyDateUtil.expirationTimeToken())
                .claim("iat", new Date())
                .signWith(SignatureAlgorithm.HS256, environment.getProperty("spring.security.key"))
                .compact();
    }

    public Object getClaim(String token, String name){
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .get(name);
    }

    public boolean validateToken(String token){
        Claims claim = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        return claim.getExpiration().after(new Date()) && claim.getSubject() != null;
    }
}
