package indipage.org.indipage.domain;

import indipage.org.indipage.domain.Relation.ArticleBookmarkRelation;
import indipage.org.indipage.domain.Relation.ArticleBookmarkRelationId;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ArticleBookmarkRelationRepository extends
        Repository<ArticleBookmarkRelation, ArticleBookmarkRelationId> {
    Optional<ArticleBookmarkRelation> findArticleBookmarkRelationByArticleBookmarkRelationId(
            ArticleBookmarkRelationId articleBookmarkRelationId);

    void save(ArticleBookmarkRelation relation);

    void delete(ArticleBookmarkRelation relation);

    List<ArticleBookmarkRelation> findAllByUser(User user);
}