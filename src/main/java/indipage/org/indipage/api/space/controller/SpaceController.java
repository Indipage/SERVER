package indipage.org.indipage.api.space.controller;

import indipage.org.indipage.api.space.controller.dto.response.SpaceDto;
import indipage.org.indipage.api.space.service.SpaceService;
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
@RequiredArgsConstructor
@RequestMapping("/space")
public class SpaceController {

    private final SpaceService spaceService;

    @GetMapping("/{spaceId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<SpaceDto> readSpace(@PathVariable final Long spaceId) {

        return ApiResponse.success(Success.READ_SPACE_SUCCESS, spaceService.readSpace(spaceId));
    }
}
