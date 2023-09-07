package indipage.org.indipage.auth.service.apple;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "oauth.apple")
public class AppleTokenValidationContext {
    private final String clientId;
    private final String nonce;
}