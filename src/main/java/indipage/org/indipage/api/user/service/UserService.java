package indipage.org.indipage.api.user.service;

import indipage.org.indipage.api.user.controller.dto.response.HasReceivedTicketResponseDto;
import indipage.org.indipage.api.user.controller.dto.response.UserDto;
import indipage.org.indipage.domain.InviteSpaceRelationRepository;
import indipage.org.indipage.domain.Relation.InviteSpaceRelation;
import indipage.org.indipage.domain.Relation.InviteSpaceRelationId;
import indipage.org.indipage.domain.Space;
import indipage.org.indipage.domain.SpaceRepository;
import indipage.org.indipage.domain.Ticket;
import indipage.org.indipage.domain.TicketRepository;
import indipage.org.indipage.domain.User;
import indipage.org.indipage.domain.UserRepository;
import indipage.org.indipage.exception.Error;
import indipage.org.indipage.exception.model.NotFoundException;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SpaceRepository spaceRepository;
    private final TicketRepository ticketRepository;
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
        // 유저 validate
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION,
                        Error.NOT_FOUND_USER_EXCEPTION.getMessage()));

        // 공간 validate
        Space space = spaceRepository.findById(spaceId).orElseThrow(
                () -> new NotFoundException(Error.NOT_FOUND_SPACE_EXCEPTION,
                        Error.NOT_FOUND_SPACE_EXCEPTION.getMessage()));

        // 공간의 티켓 조회
        Ticket ticket = ticketRepository.findBySpace(space)
                .orElseThrow(
                        () -> new NotFoundException(Error.NOT_FOUND_TICKET_EXCEPTION,
                                Error.NOT_FOUND_TICKET_EXCEPTION.getMessage()));

        // 유저의 티켓 수령 여부 조회
        Optional<InviteSpaceRelation> relation = inviteSpaceRelationRepository.findByInviteSpaceRelationId(
                InviteSpaceRelationId.newInstance(user, space));

        if (relation.isEmpty()) {
            return HasReceivedTicketResponseDto.of(ticket, false);
        }

        return HasReceivedTicketResponseDto.of(ticket, true);

    }

    public void receiveTicket(final Long userId, final Long spaceId) {
        // TODO: 티켓 수령 여부 조회 (함수 호출) -> 이미 있으면 에러 처리

        User user = findUser(userId);

        // TODO: spaceService로 뺀 findSpace 함수 호출
        Space space = spaceRepository.findById(spaceId).orElseThrow(
                () -> new NotFoundException(Error.NOT_FOUND_SPACE_EXCEPTION,
                        Error.NOT_FOUND_SPACE_EXCEPTION.getMessage()));

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

}
