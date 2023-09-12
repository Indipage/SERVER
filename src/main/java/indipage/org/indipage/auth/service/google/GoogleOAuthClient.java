package indipage.org.indipage.auth.service.google;

import indipage.org.indipage.auth.dto.OAuthUserResponseDto;
import indipage.org.indipage.auth.service.JWKs;
import indipage.org.indipage.auth.service.OAuthClient;
import indipage.org.indipage.auth.service.PublicKeyGenerator;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.PublicKey;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GoogleOAuthClient implements OAuthClient {

    private final GoogleIdTokenProcessor tokenProcessor;
    private final GooglePublicKeyClient publicKeyClient;
    private final PublicKeyGenerator publicKeyGenerator;

    @Override
    public OAuthUserResponseDto getUser(final String idToken) {
        Map<String, String> header = tokenProcessor.getParsedHeader(idToken);
        JWKs googlePublicKeys = publicKeyClient.getJWKs();

        PublicKey publicKey = publicKeyGenerator.generatePublicKey(header, googlePublicKeys);

        Claims claims = tokenProcessor.extractClaims(idToken, publicKey);
        tokenProcessor.validate(claims);
        return OAuthUserResponseDto.generateAppleUserResponseDto(claims.get("email", String.class));
    }

    @FeignClient(name = "google-public-key-client", url = "https://www.googleapis.com/oauth2/v3")
    public interface GooglePublicKeyClient {
        @GetMapping("/certs")
        JWKs getJWKs();
    }
}
