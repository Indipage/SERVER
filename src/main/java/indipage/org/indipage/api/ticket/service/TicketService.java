package indipage.org.indipage.api.ticket.service;

import indipage.org.indipage.domain.Space;
import indipage.org.indipage.domain.Ticket;
import indipage.org.indipage.domain.TicketRepository;
import indipage.org.indipage.exception.Error;
import indipage.org.indipage.exception.model.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public Ticket findTicketWithSpace(final Space space) {
        return ticketRepository.findBySpace(space)
                .orElseThrow(
                        () -> new NotFoundException(Error.NOT_FOUND_TICKET_EXCEPTION,
                                Error.NOT_FOUND_TICKET_EXCEPTION.getMessage()));
    }
}
