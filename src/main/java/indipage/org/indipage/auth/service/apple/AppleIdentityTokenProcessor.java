package indipage.org.indipage.auth.service.apple;

import indipage.org.indipage.auth.service.IdentityTokenProcessor;
import indipage.org.indipage.auth.utils.EncryptUtils;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppleIdentityTokenProcessor extends IdentityTokenProcessor {

    private final EncryptUtils encryptUtils;

    private static final String NONCE_KEY = "nonce";

    private final AppleTokenValidationContext validationContext;

    public boolean validateIdentityToken(Claims claims) {
        return claims.getIssuer().contains(validationContext.getIss()) &&
                claims.getAudience().equals(validationContext.getClientId()) &&
                encryptUtils.hashWithSHA256(claims.get(NONCE_KEY, String.class)).equals(validationContext.getNonce());
    }

}
