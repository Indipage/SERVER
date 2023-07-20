package indipage.org.indipage.api.space.controller.dto.response;

import indipage.org.indipage.domain.Ticket;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpaceVisitResponseDto {

    private String cardImageUrl;

    public static SpaceVisitResponseDto of(Ticket ticket) {
        return SpaceVisitResponseDto.builder().cardImageUrl(ticket.getCardImageUrl()).build();
    }
}
