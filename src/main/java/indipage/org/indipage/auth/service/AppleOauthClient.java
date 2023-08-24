package indipage.org.indipage.auth.service;

import indipage.org.indipage.auth.dto.OAuthUserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class AppleOauthClient implements OAuthClient {
    @Override
    public OAuthUserResponseDto getMember(String accessToken) {
        return null;
    }
}
