package indipage.org.indipage.api.user.controller.dto.response;

import indipage.org.indipage.domain.User;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UserDto {
    private String name;
    private String email;

    public static UserDto of(User user) {
        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
