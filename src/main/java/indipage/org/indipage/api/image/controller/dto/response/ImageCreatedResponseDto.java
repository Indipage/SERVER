package indipage.org.indipage.api.image.controller.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageCreatedResponseDto {
    private String imageUrl;

    public static ImageCreatedResponseDto of(String imageUrl) {
        return new ImageCreatedResponseDto(imageUrl);
    }
}
