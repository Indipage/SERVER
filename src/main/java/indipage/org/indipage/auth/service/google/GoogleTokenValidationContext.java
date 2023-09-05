package indipage.org.indipage.auth.service.google;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "oauth.google")
public class GoogleTokenValidationContext {
    private final String clientId;
    private final String iss1 = "accounts.google.com";
    private final String iss2 = "https://accounts.google.com";
}