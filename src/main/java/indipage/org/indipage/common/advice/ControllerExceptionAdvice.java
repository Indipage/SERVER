package indipage.org.indipage.common.advice;


import indipage.org.indipage.common.dto.ApiResponse;
import indipage.org.indipage.exception.Error;
import indipage.org.indipage.exception.model.CustomException;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionAdvice {

    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionAdvice.class);

    /**
     * 400 BAD_REQUEST
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    protected ApiResponse handleBindException(final BindException e) {
        FieldError fieldError = Objects.requireNonNull(e.getFieldError());
        return ApiResponse.error(Error.REQUEST_VALIDATION_EXCEPTION,
                String.format("%s는 %s", fieldError.getField(), fieldError.getDefaultMessage()));
    }

    /**
     * 413 PAYLOAD_TOO_LARGE
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    public ApiResponse fileSizeLimitExceeded(final MaxUploadSizeExceededException e) {
        return ApiResponse.error(Error.IMAGE_TOO_LARGE_EXCEPTION,
                String.format(Error.IMAGE_TOO_LARGE_EXCEPTION.getMessage()));
    }

    /**
     * 500 Internal Server
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    protected ApiResponse<Object> handleException(final Exception e) {
        logger.error("{} : {}", e.getClass(), e.getMessage());
        return ApiResponse.error(Error.INTERNAL_SERVER_ERROR);
    }

    /**
     * Custom error
     */
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ApiResponse> handleSoptException(CustomException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(ApiResponse.error(e.getError(), e.getMessage()));
    }
}
