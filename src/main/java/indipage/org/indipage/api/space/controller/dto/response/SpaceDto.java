package indipage.org.indipage.api.space.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import indipage.org.indipage.api.tag.controller.dto.response.TagDto;
import indipage.org.indipage.domain.Space;
import indipage.org.indipage.domain.SpaceType;
import indipage.org.indipage.domain.Tag;
import java.util.List;
import java.util.stream.Collectors;
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
    private List<TagDto> tagList;

    private SpaceDto(Long id, String name, String imageUrl, String roadAddress) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.roadAddress = roadAddress;
    }

    public static SpaceDto of(Space space, List<Tag> tagList) {

        return new SpaceDto(space.getId(), space.getName(), space.getImageUrl(), space.getRoadAddress(),
                space.getType(), space.getOwner(), space.getOperatingTime(), space.getClosedDays(),
                space.getIntroduction(), space.getPeculiarityTitle(),
                space.getPeculiarityContent(), space.getPeculiarityImageUrl(),
                tagList.stream().map(TagDto::of).collect(Collectors.toList()));
    }

    public static SpaceDto summaryOf(Space space) {

        return new SpaceDto(space.getId(), space.getName(), space.getImageUrl(), space.getRoadAddress());
    }
}
