package indipage.org.indipage.api.user.controller;

import indipage.org.indipage.api.article.controller.dto.response.HasSlideWeeklyArticleResponseDto;
import indipage.org.indipage.api.ticket.controller.dto.response.ReceivedCardResponseDto;
import indipage.org.indipage.api.ticket.controller.dto.response.ReceivedTicketResponseDto;
import indipage.org.indipage.api.user.controller.dto.response.HasReceivedTicketResponseDto;
import indipage.org.indipage.api.user.controller.dto.response.UserDto;
import indipage.org.indipage.api.user.service.UserService;
import indipage.org.indipage.common.dto.ApiResponse;
import indipage.org.indipage.config.resolver.UserId;
import indipage.org.indipage.exception.Success;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<UserDto> readUser(@UserId final Long userId) {
        return ApiResponse.success(Success.READ_USER_SUCCESS, userService.readUser(userId));
    }

    @GetMapping("/ticket/{spaceId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<HasReceivedTicketResponseDto> readIfUserHasReceivedTicket(@UserId final Long userId,
                                                                                 @PathVariable final Long spaceId) {
        return ApiResponse.success(Success.READ_IF_USER_HAS_RECEIVED_TICKET_SUCCESS,
                userService.readIfUserHasReceivedTicket(userId, spaceId));
    }

    @PostMapping("/ticket/{spaceId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse createReceiveTicket(@UserId final Long userId, @PathVariable final Long spaceId) {
        userService.receiveTicket(userId, spaceId);
        return ApiResponse.success(Success.CREATE_RECEIVE_TICKET_SUCCESS);
    }

    @PatchMapping("/weekly/slide")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse UpdateSlideAt(@UserId final Long userId) {
        userService.updateSlideAt(userId);
        return ApiResponse.success(Success.UPDATE_SLIDE_AT_SUCCESS);
    }


    @GetMapping("/ticket")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<ReceivedTicketResponseDto>> readReceivedTicket(@UserId final Long userId) {
        return ApiResponse.success(Success.READ_RECEIVED_TICKET_SUCCESS, userService.readReceivedTicketList(userId));
    }

    @GetMapping("/card")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<ReceivedCardResponseDto>> readReceivedCard(@UserId final Long userId) {
        return ApiResponse.success(Success.READ_RECEIVED_CARD_SUCCESS, userService.readReceivedCardList(userId));
    }

    @GetMapping("/weekly/slide")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<HasSlideWeeklyArticleResponseDto> readHasSlideWeeklyArticle(@UserId final Long userId) {
        return ApiResponse.success(Success.READ_HAS_SLIDE_WEEKLY_ARTICLE_SUCCESS,
                userService.readHasSlideWeeklyArticle(userId));
    }
}
