package indipage.org.indipage.api.article.controller.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HasSlideWeeklyArticleResponseDto {

    private boolean hasSlide;

    public static HasSlideWeeklyArticleResponseDto of(boolean hasSlide) {
        return new HasSlideWeeklyArticleResponseDto(hasSlide);
    }
}
