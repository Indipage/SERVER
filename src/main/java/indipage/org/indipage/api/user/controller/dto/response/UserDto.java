package indipage.org.indipage.api.user.controller.dto.response;

import indipage.org.indipage.domain.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UserDto {
    private Long id;
    private LocalDateTime createdAt;
    private String name;
    private String email;

    public static UserDto of(User user) {
        return UserDto.builder()
                .id(user.getId())
                .createdAt(user.getCreatedAt())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
