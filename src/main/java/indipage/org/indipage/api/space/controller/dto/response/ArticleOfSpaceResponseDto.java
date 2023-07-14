package indipage.org.indipage.api.space.controller.dto.response;

import indipage.org.indipage.domain.Article;
import indipage.org.indipage.domain.Space;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ArticleOfSpaceResponseDto {

    private String spaceName;
    private String title;
    private String spaceType;
    private Long id;
    private String imageUrl;


    public static ArticleOfSpaceResponseDto of(Space space, Article article) {
        return ArticleOfSpaceResponseDto
                .builder()
                .spaceName(space.getName())
                .title(article.getTitle())
                .spaceType(space.getType().getTypeName())
                .id(article.getId())
                .build();
    }
}
