package indipage.org.indipage.domain.Relation;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class BookRecommendationRelationId implements Serializable {

    @Column(name = "space_id", nullable = false)
    private UUID spaceId;

    @Column(name = "book_id", nullable = false)
    private Long bookId;
}
