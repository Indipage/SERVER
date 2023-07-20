package indipage.org.indipage.api.article.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import indipage.org.indipage.domain.Article;
import indipage.org.indipage.domain.Space;
import lombok.*;

import java.time.LocalDateTime;

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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime issueDate;

    private String thumbnailUrl;

    public static ArticleSummaryResponseDto of(Space space, Article article, boolean isTicketReceived) {
        return ArticleSummaryResponseDto
                .builder()
                .spaceName(space.getName())
                .title(article.getTitle())
                .spaceType(space.getType().getTypeName())
                .id(article.getId())
                .isTicketReceived(isTicketReceived)
                .issueDate(article.getIssueDate())
                .thumbnailUrl(article.getThumbnailUrl())
                .build();
    }
}
