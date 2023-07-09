package indipage.org.indipage.domain.Relation;

import indipage.org.indipage.domain.Space;
import indipage.org.indipage.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowSpaceRelation {

    @EmbeddedId
    FollowSpaceRelationId followSpaceRelationId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("spaceId")
    @JoinColumn(name = "space_id")
    private Space space;
}
