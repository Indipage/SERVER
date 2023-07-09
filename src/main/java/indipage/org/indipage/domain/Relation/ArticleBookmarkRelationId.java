package indipage.org.indipage.domain.Relation;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class ArticleBookmarkRelationId implements Serializable {

    @Column(name = "article_id", nullable = false)
    private Long articleId;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
