package indipage.org.indipage.domain.Relation;

import indipage.org.indipage.domain.Book;
import indipage.org.indipage.domain.Space;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookRecommendationRelation {

    @EmbeddedId
    BookRecommendationRelationId bookRecommendationRelationId;

    @ManyToOne
    @MapsId("spaceId")
    @JoinColumn(name = "space_id")
    private Space space;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(nullable = false)
    private String comment;
}
