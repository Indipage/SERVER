package indipage.org.indipage.auth.service.apple;

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
public class AppleOAuthClient implements OAuthClient {

    private final ApplePublicKeyClient publicKeyClient;
    private final AppleIdentityTokenProcessor identityTokenProcessor;
    private final PublicKeyGenerator publicKeyGenerator;

    @Override
    public OAuthUserResponseDto getUser(String accessToken) {
        Map<String, String> header = identityTokenProcessor.getParsedHeader(accessToken);
        JWKs JWKs = publicKeyClient.getJWKs();

        PublicKey publicKey = publicKeyGenerator.generatePublicKey(header, JWKs);

        Claims claims = identityTokenProcessor.extractClaims(accessToken, publicKey);

        identityTokenProcessor.validateIdentityToken(claims);

        return OAuthUserResponseDto.generateAppleUserResponseDto(claims.get("email", String.class));
    }

    @FeignClient(name = "apple-public-key-client", url = "https://appleid.apple.com/auth")
    public interface ApplePublicKeyClient {
        @GetMapping("/keys")
        JWKs getJWKs();
    }
}
