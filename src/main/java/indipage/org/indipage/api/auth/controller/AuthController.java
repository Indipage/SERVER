package indipage.org.indipage.api.auth.controller;

import indipage.org.indipage.api.auth.dto.request.LoginRequestDto;
import indipage.org.indipage.api.auth.dto.response.LoginResponseDto;
import indipage.org.indipage.auth.service.AuthService;
import indipage.org.indipage.common.dto.ApiResponse;
import indipage.org.indipage.exception.Success;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<LoginResponseDto> login(
            @RequestBody LoginRequestDto requestDto
    ) {
        return ApiResponse.success(Success.LOGIN_SUCCESS, authService.login(requestDto));
    }

}
