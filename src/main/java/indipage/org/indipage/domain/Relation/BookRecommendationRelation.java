package indipage.org.indipage.domain.Relation;

import indipage.org.indipage.domain.Book;
import indipage.org.indipage.domain.Space;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookRecommendationRelation {

    @EmbeddedId
    BookRecommendationRelationId bookRecommendationRelationId;

    @ManyToOne
    @MapsId("space_id")
    @JoinColumn(name = "space_id")
    private Space space;

    @ManyToOne
    @MapsId("book_id")
    @JoinColumn(name = "book_id")
    private Book book;
}
