package indipage.org.indipage.auth.service.apple;

import indipage.org.indipage.auth.dto.OAuthUserResponseDto;
import indipage.org.indipage.auth.service.OAuthClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@RequiredArgsConstructor
public class AppleOAuthClient implements OAuthClient {

    private final ApplePublicKeyClient applePublicKeyClient;

    @Override
    public OAuthUserResponseDto getMember(String accessToken) {
        return null;
    }

    @FeignClient(name = "apple-public-key-client", url = "https://appleid.apple.com/auth")
    public interface ApplePublicKeyClient {
        @GetMapping("/keys")
        ApplePublicKeys getApplePublicKey();
    }
}
