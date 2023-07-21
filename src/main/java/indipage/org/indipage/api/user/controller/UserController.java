package indipage.org.indipage.api.user.controller;

import indipage.org.indipage.api.article.controller.dto.response.HasSlideWeeklyArticleResponseDto;
import indipage.org.indipage.api.ticket.controller.dto.response.ReceivedCardResponseDto;
import indipage.org.indipage.api.ticket.controller.dto.response.ReceivedTicketResponseDto;
import indipage.org.indipage.api.user.controller.dto.response.HasReceivedTicketResponseDto;
import indipage.org.indipage.api.user.controller.dto.response.UserDto;
import indipage.org.indipage.api.user.service.UserService;
import indipage.org.indipage.common.dto.ApiResponse;
import indipage.org.indipage.exception.Success;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/weekly/slide")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse UpdateSlideAt() {
        userService.updateSlideAt(1L);
        return ApiResponse.success(Success.UPDATE_SLIDE_AT_SUCCESS);
    }


    @GetMapping("/ticket")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<ReceivedTicketResponseDto>> readReceivedTicket() {
        return ApiResponse.success(Success.READ_RECEIVED_TICKET_SUCCESS, userService.readReceivedTicketList(1L));
    }

    @GetMapping("/card")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<ReceivedCardResponseDto>> readReceivedCard() {
        return ApiResponse.success(Success.READ_RECEIVED_CARD_SUCCESS, userService.readReceivedCardList(1L));
    }

    @GetMapping("/weekly/slide")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<HasSlideWeeklyArticleResponseDto> readHasSlideWeeklyArticle() {
        return ApiResponse.success(Success.READ_HAS_SLIDE_WEEKLY_ARTICLE_SUCCESS, userService.readHasSlideWeeklyArticle(1L));
    }
}
