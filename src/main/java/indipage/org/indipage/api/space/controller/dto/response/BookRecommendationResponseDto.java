package indipage.org.indipage.api.space.controller.dto.response;

import indipage.org.indipage.api.book.controller.dto.response.BookDto;
import indipage.org.indipage.domain.Book;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class BookRecommendationResponseDto {

    private BookDto book;
    private String comment;

    public static BookRecommendationResponseDto of(Book book, String comment) {
        BookDto bookdto = BookDto.of(book);

        return BookRecommendationResponseDto.builder().book(bookdto).comment(comment).build();
    }
}
