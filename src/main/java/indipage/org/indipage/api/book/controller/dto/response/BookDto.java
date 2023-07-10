package indipage.org.indipage.api.book.controller.dto.response;

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
public class BookDto {
    private Long id;

    private String title;

    private String imageUrl;

    public static BookDto of(Book book) {

        return BookDto.builder().id(book.getId()).title(book.getTitle()).imageUrl(book.getImageUrl()).build();
    }
}
