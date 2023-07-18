package indipage.org.indipage.api.space.controller.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpaceSearchWithCategoryResponseDto {

    private String addressCategoryName;
    private List<SpaceSearchResponseDto> spaces;

    public static SpaceSearchWithCategoryResponseDto of(String addressCategoryName, List<SpaceSearchResponseDto> spaces) {
        return SpaceSearchWithCategoryResponseDto.builder()
                .addressCategoryName(addressCategoryName)
                .spaces(spaces)
                .build();
    }
}
