package indipage.org.indipage.auth;

import indipage.org.indipage.exception.Error;
import indipage.org.indipage.exception.model.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @PostConstruct
    protected void init() {
        jwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String issuedToken(String userId) {
        final Date now = new Date();

        final Claims claims = Jwts.claims().setSubject("access_token").setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + 120 * 60 * 1000L));

        claims.put("userId", userId);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .signWith(getSigningKey())
                .compact();
    }

    private Key getSigningKey() {
        final byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean verifyToken(String token) {
        try {
            final Claims claims = getBody(token);
            return true;
        } catch (RuntimeException e) {
            if (e instanceof ExpiredJwtException) {
                throw new UnauthorizedException(Error.TOKEN_TIME_EXPIRED_EXCEPTION,
                        Error.TOKEN_TIME_EXPIRED_EXCEPTION.getMessage());

            }
            return false;
        }
    }

    private Claims getBody(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getJwtContents(String token) {
        final Claims claims = getBody(token);
        return (String) claims.get("userId");
    }
}
