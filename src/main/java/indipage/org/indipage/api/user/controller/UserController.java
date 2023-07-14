package indipage.org.indipage.api.user.controller;

import indipage.org.indipage.api.article.controller.dto.response.ArticleSummaryResponseDto;
import indipage.org.indipage.api.ticket.controller.dto.response.ReceivedTicketResponseDto;
import indipage.org.indipage.api.user.controller.dto.response.HasReceivedTicketResponseDto;
import indipage.org.indipage.api.user.controller.dto.response.IsBookmarkedResponseDto;
import indipage.org.indipage.api.user.controller.dto.response.UserDto;
import indipage.org.indipage.api.user.service.UserService;
import indipage.org.indipage.common.dto.ApiResponse;
import indipage.org.indipage.exception.Success;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<UserDto> readUser() {
        return ApiResponse.success(Success.READ_USER_SUCCESS, userService.readUser(1L));
    }

    @GetMapping("/ticket/{spaceId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<HasReceivedTicketResponseDto> readIfUserHasReceivedTicket(@PathVariable final Long spaceId) {
        return ApiResponse.success(Success.READ_IF_USER_HAS_RECEIVED_TICKET_SUCCESS,
                userService.readIfUserHasReceivedTicket(1L, spaceId));
    }

    @PostMapping("/ticket/{spaceId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse createReceiveTicket(@PathVariable final Long spaceId) {
        userService.receiveTicket(1L, spaceId);
        return ApiResponse.success(Success.CREATE_RECEIVE_TICKET_SUCCESS);
    }

    @PutMapping("/space/{spaceId}/visit")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse updateVisit(@PathVariable final Long spaceId) {
        userService.visit(1L, spaceId);
        return ApiResponse.success(Success.UPDATE_VISIT_SUCCESS);
    }

    @GetMapping("/bookmark/article/{articleId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<IsBookmarkedResponseDto> readIsArticleBookMarked(@PathVariable Long articleId) {
        return ApiResponse.success(Success.READ_IS_ARTICLE_BOOKMARKED_SUCCESS,
                userService.readArticleBookmark(1L, articleId));
    }

    @PostMapping("/bookmark/article/{articleId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse createArticleBookmark(@PathVariable Long articleId) {
        userService.createArticleBookmark(1L, articleId);
        return ApiResponse.success(Success.CREATE_ARTICLE_BOOKMARK_SUCCESS);
    }

    @DeleteMapping("/bookmark/article/{articleId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse deleteArticleBookmark(@PathVariable Long articleId) {
        userService.deleteArticleBookmark(1L, articleId);
        return ApiResponse.success(Success.DELETE_ARTICLE_BOOKMARK_SUCCESS);
    }

    @GetMapping("/bookmark/space/{spaceId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<IsBookmarkedResponseDto> readIsSpaceBookmarked(@PathVariable Long spaceId) {
        return ApiResponse.success(Success.READ_IS_SPACE_BOOKMARKED_SUCCESS,
                userService.readSpaceBookmark(1L, spaceId));
    }

    @PostMapping("/bookmark/space/{spaceId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse createSpaceBookmark(@PathVariable @NotNull Long spaceId) {
        userService.createSpaceBookmark(1L, spaceId);
        return ApiResponse.success(Success.CREATE_SPACE_BOOKMARK_SUCCESS);
    }

    @DeleteMapping("/bookmark/space/{spaceId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse deleteSpaceBookmark(@PathVariable Long spaceId) {
        userService.deleteSpaceBookmark(1L, spaceId);
        return ApiResponse.success(Success.DELETE_SPACE_BOOKMARK_SUCCESS);
    }

    @GetMapping("/bookmark/article")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<ArticleSummaryResponseDto>> readArticleBookmarkList() {
        return ApiResponse.success(Success.READ_ARTICLE_BOOKMARK_LIST_SUCCESS, userService.readArticleBookmarkList(1L));
    }

    @GetMapping("/ticket")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<ReceivedTicketResponseDto>> readReceivedTicket() {
        return ApiResponse.success(Success.READ_RECEIVED_TICKET_SUCCESS, userService.readReceivedTicket(1L));
    }
}
