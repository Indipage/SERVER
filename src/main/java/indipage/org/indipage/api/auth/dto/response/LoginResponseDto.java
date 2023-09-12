package indipage.org.indipage.api.auth.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class LoginResponseDto {

    private String accessToken;

    public static LoginResponseDto of(String accessToken) {
        return new LoginResponseDto(accessToken);
    }
}
