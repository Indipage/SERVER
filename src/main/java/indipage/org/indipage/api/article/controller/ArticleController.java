package indipage.org.indipage.api.article.controller;

import indipage.org.indipage.api.article.controller.dto.response.ArticleResponseDto;
import indipage.org.indipage.api.article.controller.dto.response.ArticleSummaryResponseDto;
import indipage.org.indipage.api.article.controller.dto.response.WeeklyArticleResponseDto;
import indipage.org.indipage.api.article.service.ArticleService;
import indipage.org.indipage.common.dto.ApiResponse;
import indipage.org.indipage.config.resolver.UserId;
import indipage.org.indipage.exception.Success;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/{articleId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<ArticleResponseDto> readArticle(@UserId final Long userId, @PathVariable final Long articleId) {

        return ApiResponse.success(Success.READ_ARTICLE_SUCCESS, articleService.readArticle(articleId));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<ArticleSummaryResponseDto>> readArticleSummaryList(@UserId final Long userId) {
        return ApiResponse.success(Success.READ_ARTICLE_SUMMARY_LIST_SUCCESS,
                articleService.readArticleSummaryList(userId));
    }

    @GetMapping("/weekly")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<WeeklyArticleResponseDto> readWeeklyArticle(@UserId final Long userId) {
        return ApiResponse.success(Success.READ_WEEKLY_ARTICLE_SUCCESS, articleService.readWeeklyArticle());
    }
}
