package indipage.org.indipage.api.user.service;

import indipage.org.indipage.api.ticket.service.TicketService;
import indipage.org.indipage.api.user.controller.dto.response.HasReceivedTicketResponseDto;
import indipage.org.indipage.api.user.controller.dto.response.UserDto;
import indipage.org.indipage.domain.InviteSpaceRelationRepository;
import indipage.org.indipage.domain.Relation.InviteSpaceRelation;
import indipage.org.indipage.domain.Relation.InviteSpaceRelationId;
import indipage.org.indipage.domain.Space;
import indipage.org.indipage.domain.SpaceRepository;
import indipage.org.indipage.domain.Ticket;
import indipage.org.indipage.domain.User;
import indipage.org.indipage.domain.UserRepository;
import indipage.org.indipage.exception.Error;
import indipage.org.indipage.exception.model.ConflictException;
import indipage.org.indipage.exception.model.NotFoundException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TicketService ticketService;
    private final SpaceRepository spaceRepository;
    private final InviteSpaceRelationRepository inviteSpaceRelationRepository;

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

        // TODO: spaceService로 뺀 findSpace 함수 호출
        Space space = spaceRepository.findById(spaceId).orElseThrow(
                () -> new NotFoundException(Error.NOT_FOUND_SPACE_EXCEPTION,
                        Error.NOT_FOUND_SPACE_EXCEPTION.getMessage()));

        // TODO: 티켓 수령 여부 조회 ( 함수 호출 ) -> 없으면 에러 처리
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

}
