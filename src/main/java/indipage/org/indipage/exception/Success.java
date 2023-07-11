package indipage.org.indipage.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access =  AccessLevel.PRIVATE)
public enum Success {

    /**
     * 200 OK
     */
    LOGIN_SUCCESS(HttpStatus.OK, "로그인 성공"),
    READ_SPACE_SUCCESS(HttpStatus.OK, "서점 상세정보 조회를 성공했습니다."),
    READ_USER_SUCCESS(HttpStatus.OK, "사용자 상세정보 조회를 성공했습니다."),
    READ_IF_USER_HAS_RECEIVED_TICKET_SUCCESS(HttpStatus.OK, "초대장 수령 여부 조회를 성공했습니다."),
    /**
     * 201 CREATED
     */
    USER_CREATE_SUCCESS(HttpStatus.CREATED, "회원가입이 완료됐습니다."),
    CREATE_IMAGE_SUCCESS(HttpStatus.CREATED, "이미지 업로드를 완료했습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}

