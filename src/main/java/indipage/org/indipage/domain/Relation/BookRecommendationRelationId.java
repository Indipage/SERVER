package indipage.org.indipage.domain.Relation;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BookRecommendationRelationId implements Serializable {

    @Column(name = "space_id", nullable = false)
    private UUID spaceId;

    @Column(name = "book_id", nullable = false)
    private Long bookId;
}
