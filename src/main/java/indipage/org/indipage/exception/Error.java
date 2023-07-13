package indipage.org.indipage.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Error {

    /**
     * 400 BAD REQUEST
     */
    REQUEST_VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 요청입니다"),
    INVALID_MULTIPART_EXTENSION_EXCEPTION(HttpStatus.BAD_REQUEST, "지원하지 않는 이미지 형식입니다"),
    /**
     * 401 UNAUTHORIZED
     */

    /**
     * 404 NOT FOUND
     */
    NOT_FOUND_SPACE_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 공간입니다."),
    NOT_FOUND_ARTICLE_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 아티클입니다."),
    NOT_FOUND_USER_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다."),
    NOT_FOUND_TICKET_RECEIVE_EXCEPTION(HttpStatus.NOT_FOUND, "해당 공간의 초대장을 수령하지 않았습니다."),
    NOT_FOUND_TICKET_EXCEPTION(HttpStatus.NOT_FOUND, "해당 공간에 티켓이 존재하지 않습니다."),
    NOT_FOUND_ARTICLE_BOOKMARK_EXCEPTION(HttpStatus.NOT_FOUND, "해당 아티클에 대한 북마크가 존재하지 않습니다."),
    /**
     * 409 CONFLICT
     */
    ALREADY_INVITED_EXCEPTION(HttpStatus.CONFLICT, "이미 티켓을 수령한 상태입니다."),
    ALREADY_BOOKMARKED_ARTICLE_EXCEPTION(HttpStatus.CONFLICT, "이미 북마크한 아티클입니다."),
    ALREADY_BOOKMARKED_SPACE_EXCEPTION(HttpStatus.CONFLICT, "이미 북마크한 공간입니다."),
    /**
     * 413 PAYLOAD_TOO_LARGE
     */
    IMAGE_TOO_LARGE_EXCEPTION(HttpStatus.PAYLOAD_TOO_LARGE, "업로드 가능한 이미지 최대 용량은 3MB입니다."),
    /**
     * 500 INTERNAL SERVER ERROR
     */
    IMAGE_CREATE_FAIL_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드에 실패했습니다"),
    NO_IMAGE_FILENAME_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "저장할 이미지 명이 비어있습니다"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 에러가 발생했습니다"),
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}

