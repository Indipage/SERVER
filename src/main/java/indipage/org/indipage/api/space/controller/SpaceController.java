package indipage.org.indipage.api.space.controller;

import indipage.org.indipage.api.space.controller.dto.response.BookRecommendationResponseDto;
import indipage.org.indipage.api.space.controller.dto.response.FollowSpaceRelationResponseDto;
import indipage.org.indipage.api.space.controller.dto.response.SpaceDto;
import indipage.org.indipage.api.space.service.SpaceService;
import indipage.org.indipage.common.dto.ApiResponse;
import indipage.org.indipage.exception.Success;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/space")
public class SpaceController {

    private final SpaceService spaceService;

    @GetMapping("/{spaceId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<SpaceDto> readSpace(@PathVariable final Long spaceId) {

        return ApiResponse.success(Success.READ_SPACE_SUCCESS, spaceService.readSpace(spaceId));
    }

    @GetMapping("/{spaceId}/book")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<BookRecommendationResponseDto>> readBookRecommendation(@PathVariable final Long spaceId) {
        return ApiResponse.success(Success.READ_BOOK_RECOMMENDATION_SUCCESS,
                spaceService.readBookRecommendation(spaceId));
    }

    @GetMapping("/{spaceId}/follow")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<FollowSpaceRelationResponseDto> readFollowSpace(@PathVariable final Long spaceId) {
        return ApiResponse.success(Success.READ_FOLLOW_SPACE_SUCCESS,
                spaceService.readFollowSpace(1L, spaceId));
    }
}
