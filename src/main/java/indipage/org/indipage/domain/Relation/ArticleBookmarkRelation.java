package indipage.org.indipage.domain.Relation;

import indipage.org.indipage.domain.Article;
import indipage.org.indipage.domain.CreatedTimeBaseEntity;
import indipage.org.indipage.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleBookmarkRelation extends CreatedTimeBaseEntity {

    @EmbeddedId
    private ArticleBookmarkRelationId articleBookmarkRelationId;

    @MapsId("articleId")
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}