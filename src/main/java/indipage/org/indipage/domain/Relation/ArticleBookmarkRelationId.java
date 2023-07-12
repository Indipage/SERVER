package indipage.org.indipage.domain.Relation;

import indipage.org.indipage.domain.Article;
import indipage.org.indipage.domain.Space;
import indipage.org.indipage.domain.User;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ArticleBookmarkRelationId implements Serializable {

    @Column(name = "article_id", nullable = false)
    private Long articleId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    public static ArticleBookmarkRelationId newInstance(Article article, User user) {
        return new ArticleBookmarkRelationId(article.getId(), user.getId());
    }
}
