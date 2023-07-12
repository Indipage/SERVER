package indipage.org.indipage.api.article.controller.dto.response;

import indipage.org.indipage.domain.Article;
import indipage.org.indipage.domain.Space;
import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ArticleSummaryResponseDto {
    private String spaceName;
    private String title;
    private String spaceType;
    private Long id;
    private boolean isTicketReceived;

    public static ArticleSummaryResponseDto of(Space space, Article article, boolean isTicketReceived) {
        return ArticleSummaryResponseDto
                .builder()
                .spaceName(space.getName())
                .title(article.getTitle())
                .spaceType(space.getType().getTypeName())
                .id(article.getId())
                .isTicketReceived(isTicketReceived)
                .build();
    }
}
