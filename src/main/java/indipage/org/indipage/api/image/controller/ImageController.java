package indipage.org.indipage.api.image.controller;

import indipage.org.indipage.api.image.controller.dto.request.ImageRequestDto;
import indipage.org.indipage.api.image.controller.dto.response.ImageCreatedResponseDto;
import indipage.org.indipage.common.dto.ApiResponse;
import indipage.org.indipage.exception.Success;
import indipage.org.indipage.external.client.aws.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final S3Service s3Service;

    @PostMapping(value = "/single", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse create(
            @ModelAttribute @Valid final ImageRequestDto request) {

        String imageUrl = s3Service.uploadImage(request.getImage(), request.getFolder());
        return ApiResponse.success(Success.CREATE_IMAGE_SUCCESS, ImageCreatedResponseDto.of(imageUrl));
    }

}
