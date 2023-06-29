package indipage.org.indipage.api.image.controller;

import indipage.org.indipage.api.image.controller.dto.request.ImageRequestDto;
import indipage.org.indipage.api.image.controller.dto.response.ImageCreatedResponseDto;
import indipage.org.indipage.common.dto.ApiResponse;
import indipage.org.indipage.exception.Success;
import indipage.org.indipage.external.client.aws.S3Service;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final S3Service s3Service;

    @PostMapping(value = "/single", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse create(
            @ModelAttribute @Valid final ImageRequestDto request) {

        String imageUrl = s3Service.uploadIamge(request.getImage(), request.getFolder());
        return ApiResponse.success(Success.CREATE_IMAGE_SUCCESS, ImageCreatedResponseDto.of(imageUrl));
    }

}
