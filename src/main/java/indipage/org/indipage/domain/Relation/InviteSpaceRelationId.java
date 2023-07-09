package indipage.org.indipage.domain.Relation;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class InviteSpaceRelationId implements Serializable {


    @EmbeddedId
    InviteSpaceRelationId inviteSpaceRelationId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "space_id", nullable = false)
    private UUID spaceId;
}
