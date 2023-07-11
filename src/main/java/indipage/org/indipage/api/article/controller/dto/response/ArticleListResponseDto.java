package indipage.org.indipage.api.article.controller.dto.response;

import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ArticleListResponseDto {

    private String spaceName;
    private String title;
    private String spaceType;
    private String id;
    private String isTicketReceived;
}
