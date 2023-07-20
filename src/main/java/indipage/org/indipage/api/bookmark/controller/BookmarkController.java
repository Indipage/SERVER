package indipage.org.indipage.api.bookmark.controller;

import indipage.org.indipage.api.article.controller.dto.response.ArticleSummaryResponseDto;
import indipage.org.indipage.api.bookmark.service.BookmarkService;
import indipage.org.indipage.api.space.controller.dto.response.SpaceSummaryResponseDto;
import indipage.org.indipage.api.user.controller.dto.response.IsBookmarkedResponseDto;
import indipage.org.indipage.common.dto.ApiResponse;
import indipage.org.indipage.exception.Success;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/bookmark")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;


    @GetMapping("/article/{articleId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<IsBookmarkedResponseDto> readIsArticleBookMarked(@PathVariable Long articleId) {
        return ApiResponse.success(Success.READ_IS_ARTICLE_BOOKMARKED_SUCCESS,
                bookmarkService.readArticleBookmark(1L, articleId));
    }

    @PostMapping("/article/{articleId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse createArticleBookmark(@PathVariable Long articleId) {
        bookmarkService.createArticleBookmark(1L, articleId);
        return ApiResponse.success(Success.CREATE_ARTICLE_BOOKMARK_SUCCESS);
    }

    @DeleteMapping("/article/{articleId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse deleteArticleBookmark(@PathVariable Long articleId) {
        bookmarkService.deleteArticleBookmark(1L, articleId);
        return ApiResponse.success(Success.DELETE_ARTICLE_BOOKMARK_SUCCESS);
    }

    @GetMapping("/space/{spaceId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<IsBookmarkedResponseDto> readIsSpaceBookmarked(@PathVariable Long spaceId) {
        return ApiResponse.success(Success.READ_IS_SPACE_BOOKMARKED_SUCCESS,
                bookmarkService.readSpaceBookmark(1L, spaceId));
    }

    @PostMapping("/space/{spaceId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse createSpaceBookmark(@PathVariable @NotNull Long spaceId) {
        bookmarkService.createSpaceBookmark(1L, spaceId);
        return ApiResponse.success(Success.CREATE_SPACE_BOOKMARK_SUCCESS);
    }

    @DeleteMapping("/space/{spaceId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse deleteSpaceBookmark(@PathVariable Long spaceId) {
        bookmarkService.deleteSpaceBookmark(1L, spaceId);
        return ApiResponse.success(Success.DELETE_SPACE_BOOKMARK_SUCCESS);
    }

    @GetMapping("/article")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<ArticleSummaryResponseDto>> readArticleBookmarkList() {
        return ApiResponse.success(Success.READ_ARTICLE_BOOKMARK_LIST_SUCCESS,
                bookmarkService.readArticleBookmarkList(1L));
    }

    @GetMapping("/space")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<SpaceSummaryResponseDto>> readSpaceBookmarkList() {
        return ApiResponse.success(Success.READ_SPACE_BOOKMARK_LIST_SUCCESS, bookmarkService.readSpaceBookmarkList(1L));
    }

}
