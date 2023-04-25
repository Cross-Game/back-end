package br.com.crossgame.matchmaking.internal.security;

import br.com.crossgame.matchmaking.internal.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

@Service
@Setter
public class JwtService {

    @Value("${security.jwt.expiration}")
    private String tokenExpiration;
    @Value("${security.jwt.subscription-key}")
    private String subscriptionKey;

    public String generateToken(User user){
        Long expirationParse = Long.valueOf(this.tokenExpiration);
        LocalDateTime dateTimeTokenExpiration = LocalDateTime.now().plusMinutes(expirationParse);

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        claims.put("email", user.getEmail());
        claims.put("isOnline", user.isOnline());
        claims.put("role", user.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setExpiration(Date.from(dateTimeTokenExpiration
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .signWith(SignatureAlgorithm.HS512, this.subscriptionKey)
                .compact();
    }

    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts.parser()
                .setSigningKey(this.subscriptionKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isValidToken(String token){
        try{
            Claims claims = this.getClaims(token);
            LocalDateTime expirationDateTime = claims.getExpiration().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            return LocalDateTime.now().isBefore(expirationDateTime);
        }catch (Exception e){
            return false;
        }
    }

    public String getUsernameLogin(String token) throws ExpiredJwtException{
        return this.getClaims(token).getSubject();
    }

}
