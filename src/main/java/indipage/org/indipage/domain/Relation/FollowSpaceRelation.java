package indipage.org.indipage.domain.Relation;

import indipage.org.indipage.domain.Space;
import indipage.org.indipage.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowSpaceRelation {

    @EmbeddedId
    private FollowSpaceRelationId followSpaceRelationId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("spaceId")
    @JoinColumn(name = "space_id")
    private Space space;

    public static FollowSpaceRelation create(User user, Space space) {
        return new FollowSpaceRelation(new FollowSpaceRelationId(user.getId(), space.getId()), user, space);
    }
}
