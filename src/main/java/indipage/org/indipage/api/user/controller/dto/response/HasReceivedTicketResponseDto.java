package indipage.org.indipage.api.user.controller.dto.response;

import indipage.org.indipage.api.ticket.controller.dto.response.TicketDto;
import indipage.org.indipage.domain.Ticket;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HasReceivedTicketResponseDto {
    private TicketDto ticket;
    private boolean hasReceivedTicket;

    public static HasReceivedTicketResponseDto of(Ticket ticket, boolean hasReceivedTicket) {
        return HasReceivedTicketResponseDto.builder().ticket(TicketDto.of(ticket)).hasReceivedTicket(hasReceivedTicket)
                .build();
    }
}
