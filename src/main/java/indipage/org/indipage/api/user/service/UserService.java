package indipage.org.indipage.api.user.service;

import indipage.org.indipage.api.space.service.SpaceService;
import indipage.org.indipage.api.ticket.service.TicketService;
import indipage.org.indipage.api.user.controller.dto.response.HasReceivedTicketResponseDto;
import indipage.org.indipage.api.user.controller.dto.response.UserDto;
import indipage.org.indipage.domain.*;
import indipage.org.indipage.domain.Relation.InviteSpaceRelation;
import indipage.org.indipage.domain.Relation.InviteSpaceRelationId;
import indipage.org.indipage.exception.Error;
import indipage.org.indipage.exception.model.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SpaceService spaceService;
    private final TicketService ticketService;
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
        Space space = spaceService.findSpace(spaceId);
        Ticket ticket = ticketService.findTicketWithSpace(space);

        Optional<InviteSpaceRelation> relation = inviteSpaceRelationRepository.findByInviteSpaceRelationId(
                new InviteSpaceRelationId(userId, spaceId));

        if (relation.isEmpty()) {
            return HasReceivedTicketResponseDto.of(ticket, false);
        }

        return HasReceivedTicketResponseDto.of(ticket, true);
    }
}
