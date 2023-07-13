package indipage.org.indipage.domain;

import indipage.org.indipage.domain.Relation.ArticleBookmarkRelation;
import indipage.org.indipage.domain.Relation.ArticleBookmarkRelationId;
import indipage.org.indipage.domain.Relation.FollowSpaceRelation;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface ArticleBookmarkRelationRepository extends
        Repository<ArticleBookmarkRelation, ArticleBookmarkRelationId> {
    Optional<ArticleBookmarkRelation> findArticleBookmarkRelationByArticleBookmarkRelationId(
            ArticleBookmarkRelationId articleBookmarkRelationId);

}