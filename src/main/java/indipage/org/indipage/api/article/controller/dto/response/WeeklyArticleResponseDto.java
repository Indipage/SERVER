package indipage.org.indipage.api.article.controller.dto.response;

import indipage.org.indipage.domain.Article;
import indipage.org.indipage.domain.Space;
import lombok.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class WeeklyArticleResponseDto {

    private Long id;
    private String title;

    private String spaceName;
    private String spaceOwner;

    private long remainingDays;

    private String thumbnailUrlOfThisWeek;
    private String thumbnailUrlOfNextWeek;


    private static long calculateRemainingDays(LocalDateTime issueDate) {
        return 7 - ChronoUnit.DAYS.between(issueDate, LocalDateTime.now());
    }


    public static WeeklyArticleResponseDto of(Space space, Article articleOfThisWeek, Article articleOfNextWeek) {
        return WeeklyArticleResponseDto.builder()
                .id(articleOfThisWeek.getId())
                .title(articleOfThisWeek.getTitle())
                .spaceName(space.getName())
                .spaceOwner(space.getOwner())
                .remainingDays(calculateRemainingDays(articleOfThisWeek.getIssueDate()))
                .thumbnailUrlOfThisWeek(articleOfThisWeek.getThumbnailUrl())
                .thumbnailUrlOfNextWeek(articleOfNextWeek.getThumbnailUrl())
                .build();
    }
}
