package indipage.org.indipage.domain;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends Repository<Article, Long> {
    Optional<Article> findById(Long id);

    List<Article> findAll();
}
