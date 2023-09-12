package indipage.org.indipage.api.auth.dto.request;

import indipage.org.indipage.auth.Platform;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class LoginRequestDto {

    private String accessToken;

    private Platform platform;

}
