package indipage.org.indipage.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import indipage.org.indipage.exception.Error;
import indipage.org.indipage.exception.model.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.security.PublicKey;
import java.util.Map;

@Component
@RequiredArgsConstructor
public abstract class IdentityTokenProcessor {

    private static final String DELIMITER = "\\.";
    private static final int HEADER_INDEX = 0;

    public Map<String, String> getParsedHeader(final String identityToken) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String encodedHeader = identityToken.split(DELIMITER)[HEADER_INDEX];
            String decodedHeader = new String(Base64Utils.decodeFromUrlSafeString(encodedHeader));
            return mapper.readValue(decodedHeader, Map.class);
        } catch (JsonProcessingException e) {
            throw new UnauthorizedException(Error.INVALID_TOKEN_EXCEPTION, Error.INVALID_TOKEN_EXCEPTION.getMessage());
        }
    }

    public Claims extractClaims(final String idToken, final PublicKey publicKey) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(idToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException(Error.TOKEN_TIME_EXPIRED_EXCEPTION,
                    Error.TOKEN_TIME_EXPIRED_EXCEPTION.getMessage());
        }
    }
}
