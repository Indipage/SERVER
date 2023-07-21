package indipage.org.indipage.api.ticket.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
@JsonInclude(Include.NON_NULL)
public class TicketDto {
    private Long id;
    private String ticketImageUrl;
    private String cardImageUrl;
    private String ticketForArticleImageUrl;

    public static TicketDto of(Ticket ticket) {
        return TicketDto.builder()
                .id(ticket.getId())
                .ticketImageUrl(ticket.getTicketImageUrl())
                .cardImageUrl(ticket.getCardImageUrl())
                .ticketForArticleImageUrl(ticket.getTicketForArticleImageUrl())
                .build();
    }

}
