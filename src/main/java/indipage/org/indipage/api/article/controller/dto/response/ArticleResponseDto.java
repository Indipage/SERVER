package indipage.org.indipage.api.article.controller.dto.response;

import indipage.org.indipage.domain.Article;
import indipage.org.indipage.domain.Space;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ArticleResponseDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    private String spaceName;
    private String spaceOwner;


    public static ArticleResponseDto of(Article article, Space space) {
        return ArticleResponseDto.builder().id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .createdAt(article.getCreatedAt())
                .spaceName(space.getName())
                .spaceOwner(space.getOwner())
                .build();
    }
}
