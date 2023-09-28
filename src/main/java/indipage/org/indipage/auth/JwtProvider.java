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
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @PostConstruct
    protected void init() {
        jwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String issuedToken(String userId) {
        final Date now = new Date();

        final Claims claims = Jwts.claims().setSubject("access_token").setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + 1 * 60 * 1000L));

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

    private String verifyToken(final String token) {
        try {
            if (!token.startsWith("Bearer ")) {
                throw new UnauthorizedException(Error.INVALID_TOKEN_EXCEPTION,
                        Error.INVALID_TOKEN_EXCEPTION.getMessage());
            }

            return token.substring(7);
        } catch (RuntimeException e) {
            if (e instanceof ExpiredJwtException) {
                throw new UnauthorizedException(Error.TOKEN_TIME_EXPIRED_EXCEPTION,
                        Error.TOKEN_TIME_EXPIRED_EXCEPTION.getMessage());

            }
            throw new UnauthorizedException(Error.INVALID_TOKEN_EXCEPTION, Error.INTERNAL_SERVER_ERROR.getMessage());
        }
    }

    private Claims getBody(final String token) {
        String verifiedToken = verifyToken(token);

        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(verifiedToken)
                .getBody();
    }

    public String getJwtContents(String token) {
        final Claims claims = getBody(token);
        return (String) claims.get("userId");
    }
}
