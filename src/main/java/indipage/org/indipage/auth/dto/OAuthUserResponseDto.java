package indipage.org.indipage.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OAuthUserResponseDto {

    private static final String EMAIL_DELIMITER = "@";

    private String email;

    private String name;

    public static OAuthUserResponseDto generateAppleUserResponseDto(
            final String email
    ) {
        return OAuthUserResponseDto.builder()
                .email(email)
                .name(email.split(EMAIL_DELIMITER)[0])
                .build();
    }
}