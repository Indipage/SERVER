package indipage.org.indipage.api.ticket.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import indipage.org.indipage.domain.Relation.InviteSpaceRelation;
import indipage.org.indipage.domain.Space;
import indipage.org.indipage.domain.Ticket;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(Include.NON_NULL)
public class ReceivedCardResponseDto {
    private Long cardId;

    private String imageUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime visitedAt;

    private Long spaceId;

    public static ReceivedCardResponseDto of(Ticket ticket, InviteSpaceRelation relation, Space space) {
        return new ReceivedCardResponseDto(ticket.getId(), ticket.getCardImageUrl(), relation.getUpdatedAt(),
                space.getId());
    }
}
