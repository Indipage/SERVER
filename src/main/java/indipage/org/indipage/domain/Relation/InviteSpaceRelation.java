package indipage.org.indipage.domain.Relation;

import indipage.org.indipage.domain.ModifiedCreatedTimeBaseEntity;
import indipage.org.indipage.domain.Space;
import indipage.org.indipage.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InviteSpaceRelation extends ModifiedCreatedTimeBaseEntity {

    @EmbeddedId
    InviteSpaceRelationId inviteSpaceRelationId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("spaceId")
    @JoinColumn(name = "space_id")
    private Space space;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean hasVisited;
}
