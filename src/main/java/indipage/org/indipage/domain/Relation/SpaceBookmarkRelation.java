package indipage.org.indipage.domain.Relation;

import indipage.org.indipage.domain.CreatedTimeBaseEntity;
import indipage.org.indipage.domain.Space;
import indipage.org.indipage.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
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

}
