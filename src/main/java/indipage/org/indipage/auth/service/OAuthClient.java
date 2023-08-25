package indipage.org.indipage.auth.service;

import indipage.org.indipage.auth.dto.OAuthUserResponseDto;

public interface OAuthClient {
    OAuthUserResponseDto getUser(String token);
}
