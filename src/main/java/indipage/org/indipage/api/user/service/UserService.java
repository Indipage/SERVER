package indipage.org.indipage.api.user.service;

import indipage.org.indipage.api.ticket.service.TicketService;
import indipage.org.indipage.api.user.controller.dto.response.HasReceivedTicketResponseDto;
import indipage.org.indipage.api.user.controller.dto.response.IsBookmarkedResponseDto;
import indipage.org.indipage.api.user.controller.dto.response.UserDto;
import indipage.org.indipage.domain.Article;
import indipage.org.indipage.domain.ArticleBookmarkRelationRepository;
import indipage.org.indipage.domain.ArticleRepository;
import indipage.org.indipage.domain.InviteSpaceRelationRepository;
import indipage.org.indipage.domain.Relation.ArticleBookmarkRelation;
import indipage.org.indipage.domain.Relation.ArticleBookmarkRelationId;
import indipage.org.indipage.domain.Relation.InviteSpaceRelation;
import indipage.org.indipage.domain.Relation.InviteSpaceRelationId;
import indipage.org.indipage.domain.Relation.SpaceBookmarkRelation;
import indipage.org.indipage.domain.Relation.SpaceBookmarkRelationId;
import indipage.org.indipage.domain.Space;
import indipage.org.indipage.domain.SpaceBookmarkRelationRepository;
import indipage.org.indipage.domain.SpaceRepository;
import indipage.org.indipage.domain.Ticket;
import indipage.org.indipage.domain.User;
import indipage.org.indipage.domain.UserRepository;
import indipage.org.indipage.exception.Error;
import indipage.org.indipage.exception.model.ConflictException;
import indipage.org.indipage.exception.model.NotFoundException;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TicketService ticketService;
    private final ArticleRepository articleRepository;
    private final SpaceRepository spaceRepository;
    private final InviteSpaceRelationRepository inviteSpaceRelationRepository;
    private final ArticleBookmarkRelationRepository articleBookmarkRelationRepository;
    private final SpaceBookmarkRelationRepository spaceBookmarkRelationRepository;


    public UserDto readUser(final Long userId) {
        return UserDto.of(findUser(userId));
    }

    public User findUser(final Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION,
                        Error.NOT_FOUND_USER_EXCEPTION.getMessage()));
    }

    public HasReceivedTicketResponseDto readIfUserHasReceivedTicket(final Long userId, final Long spaceId) {

        User user = findUser(userId);
        Space space = findSpace(spaceId);
        Ticket ticket = ticketService.findTicketWithSpace(space);

        if (ticketService.isInvited(user, space)) {
            return HasReceivedTicketResponseDto.of(ticket, false);
        }
        return HasReceivedTicketResponseDto.of(ticket, true);
    }

    @Transactional(rollbackOn = Exception.class)
    public void receiveTicket(final Long userId, final Long spaceId) {

        User user = findUser(userId);
        Space space = findSpace(spaceId);

        if (ticketService.isInvited(user, space)) {
            throw new ConflictException(Error.ALREADY_INVITED_EXCEPTION,
                    Error.ALREADY_INVITED_EXCEPTION.getMessage());
        }

        // 티켓 수령하기
        inviteSpaceRelationRepository.save(InviteSpaceRelation.newInstance(user, space));
    }

    @Transactional(rollbackOn = Exception.class)
    public void visit(final Long userId, final Long spaceId) {

        User user = findUser(userId);
        Space space = findSpace(spaceId);

        InviteSpaceRelation relation = inviteSpaceRelationRepository.findByInviteSpaceRelationId(
                InviteSpaceRelationId.newInstance(user, space)).orElseThrow(
                () -> new NotFoundException(Error.NOT_FOUND_TICKET_RECEIVE_EXCEPTION,
                        Error.NOT_FOUND_TICKET_RECEIVE_EXCEPTION.getMessage()));

        // 방문하기
        relation.visit();
        inviteSpaceRelationRepository.save(relation);
    }

    public IsBookmarkedResponseDto readIsArticleBookMarked(final Long userId, final Long articleId) {

        User user = findUser(userId);
        Article article = findArticle(articleId);

        Optional<ArticleBookmarkRelation> relation = articleBookmarkRelationRepository.findArticleBookmarkRelationByArticleBookmarkRelationId(
                ArticleBookmarkRelationId.newInstance(article, user));

        if (relation.isEmpty()) {
            return IsBookmarkedResponseDto.of(false);
        }
        return IsBookmarkedResponseDto.of(true);
    }

    @Transactional(rollbackOn = Exception.class)
    public void createArticleBookmark(final Long userId, final Long articleId) {
        User user = findUser(userId);
        Article article = findArticle(articleId);

        // 북마크 검사
        if (isBookMarked(user, article)) {
            throw new ConflictException(Error.ALREADY_BOOKMARKED_ARTICLE_EXCEPTION,
                    Error.ALREADY_BOOKMARKED_ARTICLE_EXCEPTION.getMessage());
        }
        ;

        ArticleBookmarkRelation relation = ArticleBookmarkRelation.newInstance(article, user);
        articleBookmarkRelationRepository.save(relation);
    }

    @Transactional(rollbackOn = Exception.class)
    public void deleteArticleBookmark(final Long userId, final Long articleId) {
        User user = findUser(userId);
        Article article = findArticle(articleId);

        ArticleBookmarkRelation relation = findArticleBookmark(user, article);

        articleBookmarkRelationRepository.delete(relation);
    }

    public IsBookmarkedResponseDto readIsSpaceBookmarked(final Long userId, final Long spaceId) {

        User user = findUser(userId);
        Space space = findSpace(spaceId);

        if (!isBookMarked(user, space)) {
            return IsBookmarkedResponseDto.of(false);
        }
        return IsBookmarkedResponseDto.of(true);
    }

    @Transactional(rollbackOn = Exception.class)
    public void createSpaceBookmark(final Long userId, final Long spaceId) {
        User user = findUser(userId);
        Space space = findSpace(spaceId);

        // 북마크 검사
        if (isBookMarked(user, space)) {
            throw new ConflictException(Error.ALREADY_BOOKMARKED_SPACE_EXCEPTION,
                    Error.ALREADY_BOOKMARKED_SPACE_EXCEPTION.getMessage());
        }

        SpaceBookmarkRelation relation = SpaceBookmarkRelation.newInstance(space, user);
        spaceBookmarkRelationRepository.save(relation);
    }

    private Space findSpace(Long spaceId) {
        return spaceRepository.findById(spaceId).orElseThrow(
                () -> new NotFoundException(Error.NOT_FOUND_SPACE_EXCEPTION,
                        Error.NOT_FOUND_SPACE_EXCEPTION.getMessage()));
    }

    private Article findArticle(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(
                () -> new NotFoundException(Error.NOT_FOUND_ARTICLE_EXCEPTION,
                        Error.NOT_FOUND_ARTICLE_EXCEPTION.getMessage()));
    }

    private ArticleBookmarkRelation findArticleBookmark(User user, Article article) {
        return articleBookmarkRelationRepository.findArticleBookmarkRelationByArticleBookmarkRelationId(
                        ArticleBookmarkRelationId.newInstance(article, user))
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_ARTICLE_BOOKMARK_EXCEPTION,
                        Error.NOT_FOUND_ARTICLE_BOOKMARK_EXCEPTION.getMessage()));
    }

    private boolean isBookMarked(User user, Article article) {
        Optional<ArticleBookmarkRelation> relation = articleBookmarkRelationRepository.findArticleBookmarkRelationByArticleBookmarkRelationId(
                ArticleBookmarkRelationId.newInstance(article, user));

        if (relation.isEmpty()) {
            return false;
        }

        return true;
    }

    private boolean isBookMarked(User user, Space space) {
        Optional<SpaceBookmarkRelation> relation = spaceBookmarkRelationRepository.findSpaceBookmarkRelationBySpaceBookmarkRelationId(
                SpaceBookmarkRelationId.newInstance(user, space));

        if (relation.isEmpty()) {
            return false;
        }

        return true;
    }
}
