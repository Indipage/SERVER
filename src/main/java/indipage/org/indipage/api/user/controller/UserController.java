package indipage.org.indipage.api.user.controller;

import indipage.org.indipage.api.article.controller.dto.response.HasSlideWeeklyArticleResponseDto;
import indipage.org.indipage.api.ticket.controller.dto.response.ReceivedCardResponseDto;
import indipage.org.indipage.api.ticket.controller.dto.response.ReceivedTicketResponseDto;
import indipage.org.indipage.api.user.controller.dto.response.HasReceivedTicketResponseDto;
import indipage.org.indipage.api.user.controller.dto.response.UserDto;
import indipage.org.indipage.api.user.service.UserService;
import indipage.org.indipage.common.dto.ApiResponse;
import indipage.org.indipage.exception.Success;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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


    @PutMapping("/weekly/slide")
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
