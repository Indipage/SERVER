package indipage.org.indipage.domain.Relation;

import indipage.org.indipage.domain.ModifiedCreatedTimeBaseEntity;
import indipage.org.indipage.domain.Space;
import indipage.org.indipage.domain.User;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InviteSpaceRelation extends ModifiedCreatedTimeBaseEntity {

    @EmbeddedId
    private InviteSpaceRelationId inviteSpaceRelationId;

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

    private InviteSpaceRelation(InviteSpaceRelationId inviteSpaceRelationId, User user, Space space) {
        this.inviteSpaceRelationId = inviteSpaceRelationId;
        this.user = user;
        this.space = space;
    }

    public static InviteSpaceRelation newInstance(User user, Space space) {
        return new InviteSpaceRelation(InviteSpaceRelationId.newInstance(user, space), user, space);
    }

    public void visit() {
        this.hasVisited = true;
    }

}
