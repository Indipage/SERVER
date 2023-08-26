package indipage.org.indipage.auth.service;

import indipage.org.indipage.auth.JwtProvider;
import indipage.org.indipage.auth.dto.OAuthUserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final OAuthClientProvider clientProvider;
    private final JwtProvider jwtProvider;

    public void login(final String platform, final String accessToken) {
        OAuthClient client = clientProvider.getClient(platform);
        OAuthUserResponseDto oAuthUserResponseDto = client.getUser(accessToken);

        // TODO: 회원가입, 로그인 및 토큰 반환 기능 구현
    }

}
