package indipage.org.indipage.api.user.controller.dto.response;

import indipage.org.indipage.domain.User;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UserResponseDto {
    private String name;
    private String email;

    public static UserResponseDto of(User user) {
        return UserResponseDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
