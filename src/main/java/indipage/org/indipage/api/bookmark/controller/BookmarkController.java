package indipage.org.indipage.api.bookmark.controller;

import indipage.org.indipage.api.article.controller.dto.response.ArticleSummaryResponseDto;
import indipage.org.indipage.api.bookmark.service.BookmarkService;
import indipage.org.indipage.api.space.controller.dto.response.SpaceSummaryResponseDto;
import indipage.org.indipage.api.user.controller.dto.response.IsBookmarkedResponseDto;
import indipage.org.indipage.common.dto.ApiResponse;
import indipage.org.indipage.config.resolver.UserId;
import indipage.org.indipage.exception.Success;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookmark")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;


    @GetMapping("/article/{articleId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<IsBookmarkedResponseDto> readIsArticleBookMarked(@UserId final Long userId,
                                                                        @PathVariable Long articleId) {
        return ApiResponse.success(Success.READ_IS_ARTICLE_BOOKMARKED_SUCCESS,
                bookmarkService.readArticleBookmark(userId, articleId));
    }

    @PostMapping("/article/{articleId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse createArticleBookmark(@UserId final Long userId, @PathVariable Long articleId) {
        bookmarkService.createArticleBookmark(userId, articleId);
        return ApiResponse.success(Success.CREATE_ARTICLE_BOOKMARK_SUCCESS);
    }

    @DeleteMapping("/article/{articleId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse deleteArticleBookmark(@UserId final Long userId, @PathVariable Long articleId) {
        bookmarkService.deleteArticleBookmark(userId, articleId);
        return ApiResponse.success(Success.DELETE_ARTICLE_BOOKMARK_SUCCESS);
    }

    @GetMapping("/space/{spaceId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<IsBookmarkedResponseDto> readIsSpaceBookmarked(@UserId final Long userId,
                                                                      @PathVariable Long spaceId) {
        return ApiResponse.success(Success.READ_IS_SPACE_BOOKMARKED_SUCCESS,
                bookmarkService.readSpaceBookmark(userId, spaceId));
    }

    @PostMapping("/space/{spaceId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse createSpaceBookmark(@UserId final Long userId, @PathVariable @NotNull Long spaceId) {
        bookmarkService.createSpaceBookmark(userId, spaceId);
        return ApiResponse.success(Success.CREATE_SPACE_BOOKMARK_SUCCESS);
    }

    @DeleteMapping("/space/{spaceId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse deleteSpaceBookmark(@UserId final Long userId, @PathVariable Long spaceId) {
        bookmarkService.deleteSpaceBookmark(userId, spaceId);
        return ApiResponse.success(Success.DELETE_SPACE_BOOKMARK_SUCCESS);
    }

    @GetMapping("/article")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<ArticleSummaryResponseDto>> readArticleBookmarkList(@UserId final Long userId) {
        return ApiResponse.success(Success.READ_ARTICLE_BOOKMARK_LIST_SUCCESS,
                bookmarkService.readArticleBookmarkList(userId));
    }

    @GetMapping("/space")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<SpaceSummaryResponseDto>> readSpaceBookmarkList(@UserId final Long userId) {
        return ApiResponse.success(Success.READ_SPACE_BOOKMARK_LIST_SUCCESS,
                bookmarkService.readSpaceBookmarkList(userId));
    }

}
