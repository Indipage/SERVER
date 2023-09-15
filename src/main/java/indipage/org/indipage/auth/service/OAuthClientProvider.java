package indipage.org.indipage.auth.service;

import indipage.org.indipage.auth.Platform;
import indipage.org.indipage.auth.service.apple.AppleOAuthClient;
import indipage.org.indipage.auth.service.google.GoogleOAuthClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuthClientProvider {

    private static final Map<Platform, OAuthClient> oAuthClientMap = new HashMap<>();

    private final AppleOAuthClient appleOauthClient;
    private final GoogleOAuthClient googleOAuthClient;

    @PostConstruct
    void initializeOAuthClientMap() {
        oAuthClientMap.put(Platform.APPLE, appleOauthClient);
        oAuthClientMap.put(Platform.GOOGLE, googleOAuthClient);
    }

    public OAuthClient getClient(final Platform platformName) {
        return oAuthClientMap.get(platformName);
    }
}