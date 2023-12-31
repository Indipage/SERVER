package indipage.org.indipage.domain.Relation;

import indipage.org.indipage.domain.Space;
import indipage.org.indipage.domain.User;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class InviteSpaceRelationId implements Serializable {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "space_id", nullable = false)
    private Long spaceId;

    public static InviteSpaceRelationId newInstance(User user, Space space) {
        return new InviteSpaceRelationId(user.getId(), space.getId());
    }
}
