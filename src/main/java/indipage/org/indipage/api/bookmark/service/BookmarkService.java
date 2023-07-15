package indipage.org.indipage.api.bookmark.service;

import indipage.org.indipage.api.article.controller.dto.response.ArticleSummaryResponseDto;
import indipage.org.indipage.api.article.service.ArticleService;
import indipage.org.indipage.api.space.controller.dto.response.SpaceDto;
import indipage.org.indipage.api.space.service.SpaceService;
import indipage.org.indipage.api.ticket.service.TicketService;
import indipage.org.indipage.api.user.controller.dto.response.IsBookmarkedResponseDto;
import indipage.org.indipage.api.user.service.UserService;
import indipage.org.indipage.domain.Article;
import indipage.org.indipage.domain.ArticleBookmarkRelationRepository;
import indipage.org.indipage.domain.Relation.ArticleBookmarkRelation;
import indipage.org.indipage.domain.Relation.ArticleBookmarkRelationId;
import indipage.org.indipage.domain.Relation.SpaceBookmarkRelation;
import indipage.org.indipage.domain.Relation.SpaceBookmarkRelationId;
import indipage.org.indipage.domain.Space;
import indipage.org.indipage.domain.SpaceBookmarkRelationRepository;
import indipage.org.indipage.domain.User;
import indipage.org.indipage.exception.Error;
import indipage.org.indipage.exception.model.ConflictException;
import indipage.org.indipage.exception.model.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final UserService userService;
    private final ArticleService articleService;
    private final SpaceService spaceService;
    private final TicketService ticketService;
    private final ArticleBookmarkRelationRepository articleBookmarkRelationRepository;
    private final SpaceBookmarkRelationRepository spaceBookmarkRelationRepository;

    public IsBookmarkedResponseDto readArticleBookmark(final Long userId, final Long articleId) {

        User user = userService.findUser(userId);
        Article article = articleService.findArticle(articleId);

        if (!isArticleBookmarked(user, article)) {
            return IsBookmarkedResponseDto.of(false);
        }
        return IsBookmarkedResponseDto.of(true);
    }

    @Transactional(rollbackOn = Exception.class)
    public void createArticleBookmark(final Long userId, final Long articleId) {
        User user = userService.findUser(userId);
        Article article = articleService.findArticle(articleId);

        // 북마크 검사
        if (isArticleBookmarked(user, article)) {
            throw new ConflictException(Error.ALREADY_BOOKMARKED_ARTICLE_EXCEPTION,
                    Error.ALREADY_BOOKMARKED_ARTICLE_EXCEPTION.getMessage());
        }

        ArticleBookmarkRelation relation = ArticleBookmarkRelation.newInstance(article, user);
        articleBookmarkRelationRepository.save(relation);
    }

    @Transactional(rollbackOn = Exception.class)
    public void deleteArticleBookmark(final Long userId, final Long articleId) {
        User user = userService.findUser(userId);
        Article article = articleService.findArticle(articleId);

        ArticleBookmarkRelation relation = findArticleBookmark(user, article);

        articleBookmarkRelationRepository.delete(relation);
    }

    public IsBookmarkedResponseDto readSpaceBookmark(final Long userId, final Long spaceId) {

        User user = userService.findUser(userId);
        Space space = spaceService.findSpace(spaceId);

        if (!isSpaceBookmarked(user, space)) {
            return IsBookmarkedResponseDto.of(false);
        }
        return IsBookmarkedResponseDto.of(true);
    }

    @Transactional(rollbackOn = Exception.class)
    public void createSpaceBookmark(final Long userId, final Long spaceId) {
        User user = userService.findUser(userId);
        Space space = spaceService.findSpace(spaceId);

        // 북마크 검사
        if (isSpaceBookmarked(user, space)) {
            throw new ConflictException(Error.ALREADY_BOOKMARKED_SPACE_EXCEPTION,
                    Error.ALREADY_BOOKMARKED_SPACE_EXCEPTION.getMessage());
        }

        SpaceBookmarkRelation relation = SpaceBookmarkRelation.newInstance(space, user);
        spaceBookmarkRelationRepository.save(relation);
    }


    @Transactional(rollbackOn = Exception.class)
    public void deleteSpaceBookmark(final Long userId, final Long spaceId) {
        User user = userService.findUser(userId);
        Space space = spaceService.findSpace(spaceId);

        SpaceBookmarkRelation relation = findSpaceBookmark(user, space);

        spaceBookmarkRelationRepository.delete(relation);
    }

    private ArticleBookmarkRelation findArticleBookmark(User user, Article article) {
        return articleBookmarkRelationRepository.findArticleBookmarkRelationByArticleBookmarkRelationId(
                        ArticleBookmarkRelationId.newInstance(article, user))
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_ARTICLE_BOOKMARK_EXCEPTION,
                        Error.NOT_FOUND_ARTICLE_BOOKMARK_EXCEPTION.getMessage()));
    }

    private SpaceBookmarkRelation findSpaceBookmark(User user, Space space) {
        return spaceBookmarkRelationRepository.findSpaceBookmarkRelationBySpaceBookmarkRelationId(
                        SpaceBookmarkRelationId.newInstance(user, space))
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_SPACE_BOOKMARK_EXCEPTION,
                        Error.NOT_FOUND_SPACE_BOOKMARK_EXCEPTION.getMessage()));
    }

    public List<ArticleSummaryResponseDto> readArticleBookmarkList(final Long userId) {
        User user = userService.findUser(userId);
        List<ArticleSummaryResponseDto> result = new ArrayList<>();
        List<ArticleBookmarkRelation> bookmarkRelations = articleBookmarkRelationRepository.findAllByUser(user);

        for (ArticleBookmarkRelation relation : bookmarkRelations) {
            Article article = relation.getArticle();
            Space space = article.getSpace();

            boolean isInvited = ticketService.isInvited(user, space);
            result.add(ArticleSummaryResponseDto.of(article.getSpace(), article, isInvited));
        }
        return result;
    }

    public List<SpaceDto> readSpaceBookmarkList(final Long userId) {
        User user = userService.findUser(userId);
        List<SpaceDto> result = new ArrayList<>();
        List<SpaceBookmarkRelation> bookmarkRelations = spaceBookmarkRelationRepository.findAllByUser(user);

        for (SpaceBookmarkRelation relation : bookmarkRelations) {
            Space space = relation.getSpace();

            result.add(SpaceDto.summaryOf(space));
        }

        return result;
    }

    private boolean isArticleBookmarked(User user, Article article) {
        Optional<ArticleBookmarkRelation> relation = articleBookmarkRelationRepository.findArticleBookmarkRelationByArticleBookmarkRelationId(
                ArticleBookmarkRelationId.newInstance(article, user));

        if (relation.isEmpty()) {
            return false;
        }

        return true;
    }

    private boolean isSpaceBookmarked(User user, Space space) {
        Optional<SpaceBookmarkRelation> relation = spaceBookmarkRelationRepository.findSpaceBookmarkRelationBySpaceBookmarkRelationId(
                SpaceBookmarkRelationId.newInstance(user, space));

        if (relation.isEmpty()) {
            return false;
        }

        return true;
    }

}
