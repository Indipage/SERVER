package indipage.org.indipage.api.space.controller.dto.response;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FollowSpaceRelationResponseDto {

    private Boolean isFollowed;

    public static FollowSpaceRelationResponseDto of(boolean isFollow) {
        return FollowSpaceRelationResponseDto.builder().isFollowed(isFollow).build();
    }
}
