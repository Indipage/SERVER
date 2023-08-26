package indipage.org.indipage.api.auth.controller;

import indipage.org.indipage.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void login(
            @RequestParam(value = "platform") final String platform,
            @RequestParam(value = "token") final String token
    ) {
        authService.login(platform, token);
    }

}
