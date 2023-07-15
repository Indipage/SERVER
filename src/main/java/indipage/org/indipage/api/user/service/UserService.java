package indipage.org.indipage.api.user.service;

import indipage.org.indipage.api.article.controller.dto.response.HasSlideWeeklyArticleResponseDto;
import indipage.org.indipage.api.ticket.controller.dto.response.ReceivedCardResponseDto;
import indipage.org.indipage.api.ticket.controller.dto.response.ReceivedTicketResponseDto;
import indipage.org.indipage.api.ticket.service.TicketService;
import indipage.org.indipage.api.user.controller.dto.response.HasReceivedTicketResponseDto;
import indipage.org.indipage.api.user.controller.dto.response.UserDto;
import indipage.org.indipage.domain.Article;
import indipage.org.indipage.domain.ArticleRepository;
import indipage.org.indipage.domain.InviteSpaceRelationRepository;
import indipage.org.indipage.domain.Relation.InviteSpaceRelation;
import indipage.org.indipage.domain.Relation.InviteSpaceRelationId;
import indipage.org.indipage.domain.Space;
import indipage.org.indipage.domain.SpaceBookmarkRelationRepository;
import indipage.org.indipage.domain.SpaceRepository;
import indipage.org.indipage.domain.Ticket;
import indipage.org.indipage.domain.User;
import indipage.org.indipage.domain.UserRepository;
import indipage.org.indipage.exception.Error;
import indipage.org.indipage.exception.model.ConflictException;
import indipage.org.indipage.exception.model.NotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

        if (!ticketService.isInvited(user, space)) {
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

    private Space findSpace(Long spaceId) {
        return spaceRepository.findById(spaceId).orElseThrow(
                () -> new NotFoundException(Error.NOT_FOUND_SPACE_EXCEPTION,
                        Error.NOT_FOUND_SPACE_EXCEPTION.getMessage()));
    }


    @Transactional
    public void updateSlideAt(final Long userId) {
        User user = findUser(userId);
        user.updateSlideAt();
    }


    public List<ReceivedTicketResponseDto> readReceivedTicketList(final Long userId) {
        User user = findUser(userId);
        List<ReceivedTicketResponseDto> result = new ArrayList<>();
        List<InviteSpaceRelation> inviteRelations = inviteSpaceRelationRepository.findAllByUserAndHasVisitedIsFalseOrderByCreatedAtDesc(
                user);

        for (InviteSpaceRelation relation : inviteRelations) {
            Space spaceOfTicket = relation.getSpace();
            Ticket ticket = ticketService.findTicketWithSpace(spaceOfTicket);

            result.add(ReceivedTicketResponseDto.of(ticket, spaceOfTicket));
        }
        return result;
    }

    public List<ReceivedCardResponseDto> readReceivedCardList(final Long userId) {
        User user = findUser(userId);
        List<ReceivedCardResponseDto> result = new ArrayList<>();
        List<InviteSpaceRelation> inviteRelations = inviteSpaceRelationRepository.findAllByUserAndHasVisitedIsTrueOrderByUpdatedAtDesc(
                user);

        for (InviteSpaceRelation relation : inviteRelations) {
            Space spaceOfTicket = relation.getSpace();
            Ticket ticket = ticketService.findTicketWithSpace(spaceOfTicket);

            result.add(ReceivedCardResponseDto.of(ticket, relation, spaceOfTicket));
        }

        return result;
    }

    public HasSlideWeeklyArticleResponseDto readHasSlideWeeklyArticle(final Long userId) {
        User user = findUser(userId);

        LocalDateTime now = LocalDateTime.now();

        Article articleOfThisWeek = findArticleOfThisWeek(now);
        return HasSlideWeeklyArticleResponseDto.of(user.getSlideAt().isAfter(articleOfThisWeek.getIssueDate()));
    }

    public Article findArticleOfThisWeek(LocalDateTime now) {
        return articleRepository.findTop1ByIssueDateIsBeforeOrderByIssueDateDesc(now).orElseThrow(
                () -> new NotFoundException(Error.NOT_FOUND_ARTICLE_OF_THIS_WEEK_EXCEPTION,
                        Error.NOT_FOUND_ARTICLE_OF_THIS_WEEK_EXCEPTION.getMessage()));
    }
}
