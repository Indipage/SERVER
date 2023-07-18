package indipage.org.indipage.api.space.controller.dto.response;

import indipage.org.indipage.domain.Space;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpaceSearchResponseDto {

    private Long spaceId;
    private String spaceName;
    private String address;

    public static SpaceSearchResponseDto of(Space space) {
        return SpaceSearchResponseDto.builder()
                .spaceId(space.getId())
                .spaceName(space.getName())
                .address(space.getAddress().toString())
                .build();
    }
}
