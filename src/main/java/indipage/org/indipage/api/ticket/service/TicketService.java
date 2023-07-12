package indipage.org.indipage.api.ticket.service;

import indipage.org.indipage.domain.*;
import indipage.org.indipage.domain.Relation.InviteSpaceRelationId;
import indipage.org.indipage.exception.Error;
import indipage.org.indipage.exception.model.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final InviteSpaceRelationRepository inviteSpaceRelationRepository;

    public Ticket findTicketWithSpace(final Space space) {
        return ticketRepository.findBySpace(space)
                .orElseThrow(
                        () -> new NotFoundException(Error.NOT_FOUND_TICKET_EXCEPTION,
                                Error.NOT_FOUND_TICKET_EXCEPTION.getMessage()));
    }

    public boolean isInvited(User user, Space space) {
        return inviteSpaceRelationRepository.findByInviteSpaceRelationId(
                new InviteSpaceRelationId(user.getId(), space.getId())).isPresent();
    }
}
