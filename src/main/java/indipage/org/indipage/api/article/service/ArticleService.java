package indipage.org.indipage.api.article.service;

import indipage.org.indipage.api.article.controller.dto.response.ArticleResponseDto;
import indipage.org.indipage.domain.Article;
import indipage.org.indipage.domain.ArticleRepository;
import indipage.org.indipage.exception.Error;
import indipage.org.indipage.exception.model.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleResponseDto readArticle(final Long articleId) {
        Article article = findArticle(articleId);
        return ArticleResponseDto.of(article, article.getSpace());
    }

    public Article findArticle(final Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(
                () -> new NotFoundException(Error.NOT_FOUND_ARTICLE_EXCEPTION,
                        Error.NOT_FOUND_ARTICLE_EXCEPTION.getMessage()));
    }
}
