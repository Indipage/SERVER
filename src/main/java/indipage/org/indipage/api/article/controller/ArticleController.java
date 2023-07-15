package indipage.org.indipage.api.article.controller;

import indipage.org.indipage.api.article.controller.dto.response.ArticleResponseDto;
import indipage.org.indipage.api.article.controller.dto.response.ArticleSummaryResponseDto;
import indipage.org.indipage.api.article.controller.dto.response.WeeklyArticleResponseDto;
import indipage.org.indipage.api.article.service.ArticleService;
import indipage.org.indipage.common.dto.ApiResponse;
import indipage.org.indipage.exception.Success;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/{articleId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<ArticleResponseDto> readArticle(@PathVariable final Long articleId) {

        return ApiResponse.success(Success.READ_ARTICLE_SUCCESS, articleService.readArticle(articleId));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<ArticleSummaryResponseDto>> readArticleSummaryList() {
        return ApiResponse.success(Success.READ_ARTICLE_SUMMARY_LIST_SUCCESS, articleService.readArticleSummaryList(1L));
    }

    @GetMapping("/weekly")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<WeeklyArticleResponseDto> readWeeklyArticle() {
        return ApiResponse.success(Success.READ_WEEKLY_ARTICLE_SUCCESS, articleService.readWeeklyArticle());
    }
}
