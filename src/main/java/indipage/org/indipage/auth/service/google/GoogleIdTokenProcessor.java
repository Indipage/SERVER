package indipage.org.indipage.auth.service.google;

import indipage.org.indipage.auth.service.IdentityTokenProcessor;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GoogleIdTokenProcessor extends IdentityTokenProcessor {

    private final GoogleTokenValidationContext validationContext;

    private static final String iss1 = "accounts.google.com";
    private static final String iss2 = "https://accounts.google.com";

    public boolean validate(Claims claims) {
        return (claims.getIssuer().contains(iss1) || claims.getIssuer().contains(iss2) &&
                claims.getAudience().equals(validationContext.getClientId()));
    }
}
