package indipage.org.indipage.domain.Relation;

import indipage.org.indipage.domain.CreatedTimeBaseEntity;
import indipage.org.indipage.domain.Space;
import indipage.org.indipage.domain.User;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SpaceBookmarkRelation extends CreatedTimeBaseEntity {
    @EmbeddedId
    private SpaceBookmarkRelationId spaceBookmarkRelationId;

    @MapsId("spaceId")
    @ManyToOne
    @JoinColumn(name = "space_id")
    private Space space;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public static SpaceBookmarkRelation newInstance(Space space, User user) {
        return new SpaceBookmarkRelation(
                SpaceBookmarkRelationId.newInstance(user, space), space, user);
    }

}
