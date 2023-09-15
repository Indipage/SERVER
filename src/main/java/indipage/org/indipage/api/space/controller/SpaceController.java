package indipage.org.indipage.api.space.controller;

import indipage.org.indipage.api.space.controller.dto.response.*;
import indipage.org.indipage.api.space.service.SpaceService;
import indipage.org.indipage.common.dto.ApiResponse;
import indipage.org.indipage.config.resolver.UserId;
import indipage.org.indipage.exception.Success;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/space")
public class SpaceController {

    private final SpaceService spaceService;

    @GetMapping("/{spaceId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<SpaceDto> readSpace(@UserId final Long userId, @PathVariable final Long spaceId) {

        return ApiResponse.success(Success.READ_SPACE_SUCCESS, spaceService.readSpace(spaceId));
    }

    @GetMapping("/{spaceId}/book")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<BookRecommendationResponseDto>> readBookRecommendation(@UserId final Long userId, @PathVariable final Long spaceId) {
        return ApiResponse.success(Success.READ_BOOK_RECOMMENDATION_SUCCESS,
                spaceService.readBookRecommendation(spaceId));
    }

    @GetMapping("/{spaceId}/follow")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<FollowSpaceRelationResponseDto> readFollowSpace(@UserId final Long userId, @PathVariable final Long spaceId) {
        return ApiResponse.success(Success.READ_FOLLOW_SPACE_SUCCESS,
                spaceService.readFollowSpace(userId, spaceId));
    }

    @PostMapping("/{spaceId}/follow")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse createFollowSpace(@UserId final Long userId, @PathVariable final Long spaceId) {
        spaceService.createFollowSpace(userId, spaceId);
        return ApiResponse.success(Success.CREATE_FOLLOW_SPACE_SUCCESS);
    }

    @GetMapping("/{spaceId}/article")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<ArticleOfSpaceResponseDto> readArticleOfSpace(@UserId final Long userId, @PathVariable final Long spaceId) {
        return ApiResponse.success(Success.READ_ARTICLE_OF_SPACE_SUCCESS, spaceService.readArticleOfSpace(spaceId));
    }

    @PatchMapping("/{spaceId}/visit")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<SpaceVisitResponseDto> updateVisit(@UserId final Long userId, @PathVariable final Long spaceId) {

        return ApiResponse.success(Success.UPDATE_VISIT_SUCCESS, spaceService.visit(userId, spaceId));
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<SpaceSearchResponseDto>> searchWithAddress(@RequestParam(value = "keyword") Optional<String> searchWord, @UserId final Long userId) {
        return ApiResponse.success(Success.SEARCH_SPACE_SUCCESS, spaceService.searchSpace(searchWord));
    }
}
