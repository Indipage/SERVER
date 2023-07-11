package indipage.org.indipage.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Success {

    /**
     * 200 OK
     */
    LOGIN_SUCCESS(HttpStatus.OK, "로그인 성공"),
    READ_SPACE_SUCCESS(HttpStatus.OK, "서점 상세정보 조회를 성공했습니다."),
    READ_BOOK_RECOMMENDATION_SUCCESS(HttpStatus.OK, "서점의 추천 서가 조회를 성공했습니다."),
    READ_ARTICLE_SUCCESS(HttpStatus.OK, "아티클 상세정보 조회를 성공했습니다."),
    READ_USER_SUCCESS(HttpStatus.OK, "사용자 상세정보 조회를 성공했습니다."),
    READ_FOLLOW_SPACE_SUCCESS(HttpStatus.OK, "조르기 여부 조회를 성공했습니다."),
    /**
     * 201 CREATED
     */
    USER_CREATE_SUCCESS(HttpStatus.CREATED, "회원가입이 완료됐습니다."),
    CREATE_IMAGE_SUCCESS(HttpStatus.CREATED, "이미지 업로드를 완료했습니다."),
    CREATE_FOLLOW_SPACE_SUCCESS(HttpStatus.CREATED, "조르기 등록에 성공했습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}

