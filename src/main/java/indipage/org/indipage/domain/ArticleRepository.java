package indipage.org.indipage.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends Repository<Article, Long> {
    Optional<Article> findById(Long id);

    List<Article> findAll();

    Optional<Article> findArticleBySpace(Space space);

    @Query("SELECT a FROM Article AS a WHERE a.issueDate <= ?1 ORDER BY a.issueDate DESC")
    List<Article> findIssuedArticle(LocalDateTime now);
}
