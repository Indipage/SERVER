package indipage.org.indipage.api.image.controller.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ImageRequestDto {
    @NotNull
    private MultipartFile image;

    @NotBlank
    @Pattern(regexp = "(^book$)|(^bookstore$)", message = "지원하지 않는 폴더입니다")
    private String folder;
}
