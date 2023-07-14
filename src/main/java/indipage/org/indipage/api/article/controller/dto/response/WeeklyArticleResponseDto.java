package indipage.org.indipage.api.article.controller.dto.response;

import indipage.org.indipage.domain.Article;
import indipage.org.indipage.domain.Space;
import indipage.org.indipage.domain.User;
import lombok.*;

import java.time.LocalDate;
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

    private Boolean hasSlide;


    private static long calculateRemainingDays(LocalDateTime issueDate) {
        return 7 - ChronoUnit.DAYS.between(issueDate, LocalDateTime.now());
    }

    private static boolean getHasSlide(LocalDateTime issueDate, User user) {
        if (user.getSlideAt() == null) {
            return false;
        }
        LocalDate issueDateToLocalDate = issueDate.toLocalDate();
        LocalDate slideAtToLocalDate = user.getSlideAt().toLocalDate();

        return slideAtToLocalDate.isEqual(issueDateToLocalDate) || slideAtToLocalDate.isAfter(issueDateToLocalDate);
    }

    public static WeeklyArticleResponseDto of(Space space, Article article, User user) {
        return WeeklyArticleResponseDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .spaceName(space.getName())
                .spaceOwner(space.getOwner())
                .remainingDays(calculateRemainingDays(article.getIssueDate()))
                .hasSlide(getHasSlide(article.getIssueDate(), user))
                .build();
    }
}
