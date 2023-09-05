package indipage.org.indipage.auth.service.google;

import indipage.org.indipage.auth.service.IdentityTokenProcessor;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GoogleIdTokenProcessor extends IdentityTokenProcessor {

    private final GoogleTokenValidationContext validationContext;

    public boolean validate(Claims claims) {
        return (claims.getIssuer().contains(validationContext.getIss1()) || claims.getIssuer().contains(validationContext.getIss2())) &&
                claims.getAudience().equals(validationContext.getClientId());
    }
}
