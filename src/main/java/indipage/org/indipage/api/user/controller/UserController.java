package indipage.org.indipage.api.user.controller;

import indipage.org.indipage.api.user.controller.dto.response.HasReceivedTicketResponseDto;
import indipage.org.indipage.api.user.controller.dto.response.UserDto;
import indipage.org.indipage.api.user.service.UserService;
import indipage.org.indipage.common.dto.ApiResponse;
import indipage.org.indipage.exception.Success;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
