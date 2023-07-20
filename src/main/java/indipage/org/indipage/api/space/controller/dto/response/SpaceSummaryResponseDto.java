package indipage.org.indipage.api.space.controller.dto.response;

import indipage.org.indipage.domain.Space;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SpaceSummaryResponseDto {

    private Long id;
    private String name;
    private String imageUrl;
    private String address;

    public static SpaceSummaryResponseDto of(Space space) {
        return new SpaceSummaryResponseDto(space.getId(), space.getName(), space.getImageUrl(), space.getAddress().toString());
    }
}