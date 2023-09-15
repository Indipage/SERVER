package indipage.org.indipage.auth.service;

import indipage.org.indipage.api.auth.dto.request.LoginRequestDto;
import indipage.org.indipage.api.auth.dto.response.LoginResponseDto;
import indipage.org.indipage.auth.JwtProvider;
import indipage.org.indipage.auth.Platform;
import indipage.org.indipage.auth.dto.OAuthUserResponseDto;
import indipage.org.indipage.domain.User;
import indipage.org.indipage.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final OAuthClientProvider clientProvider;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public LoginResponseDto login(final LoginRequestDto requestDto) {
        OAuthClient client = clientProvider.getClient(requestDto.getPlatform());
        OAuthUserResponseDto responseDto = client.getUser(requestDto.getAccessToken());

        String accessToken = userRepository.findByEmail(responseDto.getEmail())
                .map(user -> jwtProvider.issuedToken((user.getId()).toString()))
                .orElseGet(() -> signUp(requestDto.getPlatform(), responseDto));

        return LoginResponseDto.of(accessToken);
    }

    @Transactional
    public String signUp(final Platform platform, final OAuthUserResponseDto responseDto) {
        User user = userRepository.save(
                User.of(responseDto.getEmail(), responseDto.getName(), platform));

        return jwtProvider.issuedToken(user.getId().toString());
    }

}
