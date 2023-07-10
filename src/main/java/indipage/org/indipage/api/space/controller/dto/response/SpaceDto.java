package indipage.org.indipage.api.space.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import indipage.org.indipage.domain.Space;
import indipage.org.indipage.domain.SpaceType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(Include.NON_NULL)
public class SpaceDto {
    private Long id;
    private String name;
    private String imageUrl;
    private String roadAddress;
    private SpaceType type;
    private String owner;
    private String operatingTime;
    private String closedDays;
    private String introduction;
    private String peculiarityTitle;
    private String peculiarityContent;
    private String peculiarityImageUrl;

    public static SpaceDto of(Space space) {

        return new SpaceDto(space.getId(), space.getName(), space.getImageUrl(), space.getRoadAddress(),
                space.getType(), space.getOwner(), space.getOperatingTime(), space.getClosedDays(),
                space.getIntroduction(), space.getPeculiarityTitle(),
                space.getPeculiarityContent(), space.getPeculiarityImageUrl());
    }
}
