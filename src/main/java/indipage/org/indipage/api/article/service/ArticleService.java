package indipage.org.indipage.api.article.service;

import indipage.org.indipage.api.article.controller.dto.response.ArticleResponseDto;
import indipage.org.indipage.api.article.controller.dto.response.ArticleSummaryResponseDto;
import indipage.org.indipage.api.article.controller.dto.response.WeeklyArticleResponseDto;
import indipage.org.indipage.api.ticket.service.TicketService;
import indipage.org.indipage.api.user.service.UserService;
import indipage.org.indipage.domain.Article;
import indipage.org.indipage.domain.ArticleRepository;
import indipage.org.indipage.domain.Space;
import indipage.org.indipage.domain.User;
import indipage.org.indipage.exception.Error;
import indipage.org.indipage.exception.model.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserService userService;
    private final TicketService ticketService;

    public ArticleResponseDto readArticle(final Long articleId) {
        Article article = findArticle(articleId);
        return ArticleResponseDto.of(article, article.getSpace());
    }

    public Article findArticle(final Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(
                () -> new NotFoundException(Error.NOT_FOUND_ARTICLE_EXCEPTION,
                        Error.NOT_FOUND_ARTICLE_EXCEPTION.getMessage()));
    }

    public List<ArticleSummaryResponseDto> readArticleSummaryList(final Long userId) {
        List<Article> articles = articleRepository.findAll();
        List<ArticleSummaryResponseDto> result = new ArrayList<>();
        User user = userService.findUser(userId);

        for (Article article : articles) {
            Space space = article.getSpace();
            boolean isInvited = ticketService.isInvited(user, space);
            result.add(ArticleSummaryResponseDto.of(space, article, isInvited));
        }

        return result;
    }

    public Article findArticleBySpace(final Space space) {
        return articleRepository.findArticleBySpace(space).orElseThrow(
                () -> new NotFoundException(Error.NOT_FOUND_SPACE_OF_ARTICLE_EXCEPTION,
                        Error.NOT_FOUND_SPACE_OF_ARTICLE_EXCEPTION.getMessage()));
    }

    public WeeklyArticleResponseDto readWeeklyArticle() {
        LocalDateTime now = LocalDateTime.now();

        Article articleOfThisWeek = findArticleOfThisWeek(now);
        Article articleOfNextWeek = findArticleOfNextWeek(now);

        return WeeklyArticleResponseDto.of(articleOfThisWeek.getSpace(), articleOfThisWeek, articleOfNextWeek);
    }

    private Article findArticleOfThisWeek(LocalDateTime now) {
        return articleRepository.findTop1ByIssueDateIsBeforeOrderByIssueDateDesc(now).orElseThrow(
                () -> new NotFoundException(Error.NOT_FOUND_ARTICLE_OF_THIS_WEEK_EXCEPTION,
                        Error.NOT_FOUND_ARTICLE_OF_THIS_WEEK_EXCEPTION.getMessage()));
    }

    private Article findArticleOfNextWeek(LocalDateTime now) {
        return articleRepository.findTop1ByIssueDateIsAfterOrderByIssueDate(now).orElseThrow(
                () -> new NotFoundException(Error.NOT_FOUND_ARTICLE_OF_NEXT_WEEK_EXCEPTION,
                        Error.NOT_FOUND_ARTICLE_OF_NEXT_WEEK_EXCEPTION.getMessage()));
    }
}
