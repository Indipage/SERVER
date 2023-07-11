package indipage.org.indipage.domain;

import indipage.org.indipage.domain.Relation.FollowSpaceRelation;
import indipage.org.indipage.domain.Relation.FollowSpaceRelationId;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface FollowSpaceRelationRepository extends Repository<FollowSpaceRelation, FollowSpaceRelationId> {
    Optional<FollowSpaceRelation> findFollowSpaceRelationByFollowSpaceRelationId(FollowSpaceRelationId followSpaceRelationId);
}

