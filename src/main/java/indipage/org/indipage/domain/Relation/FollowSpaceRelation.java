package indipage.org.indipage.domain.Relation;

import indipage.org.indipage.domain.CreatedTimeBaseEntity;
import javax.persistence.EmbeddedId;

public class FollowSpaceRelation extends CreatedTimeBaseEntity {

    @EmbeddedId
    FollowSpaceRelationId followSpaceRelationId;


}
