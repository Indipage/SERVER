package indipage.org.indipage.auth.service.apple;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import indipage.org.indipage.auth.utils.EncryptUtils;
import indipage.org.indipage.exception.Error;
import indipage.org.indipage.exception.model.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.security.PublicKey;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AppleIdentityTokenProcessor {

    @Value("${oauth.apple.iss}")
    private String iss;
    @Value("${oauth.apple.client-id}")
    private String clientId;
    @Value("${oauth.apple.nonce}")
    private String nonce;
    private static final String DELIMITER = "\\.";
    private static final int HEADER_INDEX = 0;
    private static final String NONCE_KEY = "nonce";

    private final EncryptUtils encryptUtils;

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

    public Claims extractClaimsFromIdentityToken(final String idToken, final PublicKey publicKey) {
        try {
            return Jwts.parser()
                    .setSigningKey(publicKey)
                    .parseClaimsJws(idToken)
                    .getBody();

        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException(Error.TOKEN_TIME_EXPIRED_EXCEPTION, Error.TOKEN_TIME_EXPIRED_EXCEPTION.getMessage());
        }
    }

}
