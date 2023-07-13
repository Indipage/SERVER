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
    UPDATE_VISIT_SUCCESS(HttpStatus.OK, "공간 방문에 성공했습니다."),
    READ_IF_USER_HAS_RECEIVED_TICKET_SUCCESS(HttpStatus.OK, "티켓 수령 여부 조회를 성공했습니다."),
    READ_ARTICLE_SUMMARY_LIST_SUCCESS(HttpStatus.OK, "아티클 전체 목록 조회에 성공했습니다."),
    READ_IS_ARTICLE_BOOKMARKED_SUCCESS(HttpStatus.OK, "아티클 북마크 여부 조회 성공"),
    READ_IS_SPACE_BOOKMARKED_SUCCESS(HttpStatus.OK, "공간 북마크 여부 조회 성공"),
    /**
     * 201 CREATED
     */
    USER_CREATE_SUCCESS(HttpStatus.CREATED, "회원가입이 완료됐습니다."),

    CREATE_IMAGE_SUCCESS(HttpStatus.CREATED, "이미지 업로드를 완료했습니다."),

    CREATE_FOLLOW_SPACE_SUCCESS(HttpStatus.CREATED, "조르기 등록에 성공했습니다."),
    CREATE_RECEIVE_TICKET_SUCCESS(HttpStatus.CREATED, "티켓 수령에 성공했습니다."),
    CREATE_ARTICLE_BOOKMARK_SUCCESS(HttpStatus.CREATED, "아티클 북마크 등록에 성공했습니다."),
    CREATE_SPACE_BOOKMARK_SUCCESS(HttpStatus.CREATED, "공간 북마크 등록에 성공했습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}

