package indipage.org.indipage.api.user.controller.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IsBookmarkedResponseDto {
    private boolean isBookmarked;

    public static IsBookmarkedResponseDto of(boolean isBookMarked) {
        return new IsBookmarkedResponseDto(isBookMarked);
    }
}
