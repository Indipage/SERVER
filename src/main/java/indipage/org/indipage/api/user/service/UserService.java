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
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION,
                        Error.NOT_FOUND_USER_EXCEPTION.getMessage()));

        return UserDto.of(user);
    }

    public HasReceivedTicketResponseDto readIfUserHasReceivedTicket(final Long userId, final Long spaceId) {
        // 유저 validate
        userRepository.findById(userId).orElseThrow(
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
                new InviteSpaceRelationId(userId, spaceId));

        if (relation.isEmpty()) {
            return HasReceivedTicketResponseDto.of(ticket, false);
        }

        return HasReceivedTicketResponseDto.of(ticket, true);

    }
}
