package indipage.org.indipage.domain;

import org.springframework.data.repository.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends Repository<Article, Long> {
    Optional<Article> findById(Long id);

    Optional<Article> findArticleBySpaceAndIssueDateIsBefore(Space space, LocalDateTime issueDate);

    Optional<Article> findArticleBySpace(Space space);

    Optional<Article> findTop1ByIssueDateIsBeforeOrderByIssueDateDesc(LocalDateTime now);

    Optional<Article> findTop1ByIssueDateIsAfterOrderByIssueDate(LocalDateTime now);
    List<Article> findArticleByIssueDateIsBefore(LocalDateTime now);
}
