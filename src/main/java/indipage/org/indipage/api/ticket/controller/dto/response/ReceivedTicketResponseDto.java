package indipage.org.indipage.api.ticket.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import indipage.org.indipage.domain.Space;
import indipage.org.indipage.domain.Ticket;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(Include.NON_NULL)
public class ReceivedTicketResponseDto {
    private Long ticketId;

    private String imageUrl;

    private Long spaceId;

    public static ReceivedTicketResponseDto of(Ticket ticket, Space space) {
        return new ReceivedTicketResponseDto(ticket.getId(), ticket.getTicketImageUrl(), space.getId());
    }
}
