package indipage.org.indipage.config.resolver;

import indipage.org.indipage.auth.JwtService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
public class UserIdResolver implements HandlerMethodArgumentResolver {
    private final JwtService jwtService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserId.class) && Long.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(@NotNull MethodParameter parameter, ModelAndViewContainer modelAndViewContainer,
                                  @NotNull NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        final HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        final String token = request.getHeader("Authorization");

        if (!jwtService.verifyToken(token)) {
            throw new RuntimeException(
                    String.format("USER_ID를 가져오지 못했습니다. (%s - %s)", parameter.getClass(), parameter.getMethod()));
        }

        // 유저 아이디 반환
        final String tokenContents = jwtService.getJwtContents(token);
        try {
            return Long.parseLong(tokenContents);
        } catch (NumberFormatException e) {
            throw new RuntimeException(
                    String.format("USER_ID를 가져오지 못했습니다. (%s - %s)", parameter.getClass(), parameter.getMethod()));
        }
    }
}
