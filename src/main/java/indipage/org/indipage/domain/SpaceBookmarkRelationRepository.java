package indipage.org.indipage.domain;

import indipage.org.indipage.domain.Relation.SpaceBookmarkRelation;
import indipage.org.indipage.domain.Relation.SpaceBookmarkRelationId;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface SpaceBookmarkRelationRepository extends
        Repository<SpaceBookmarkRelation, SpaceBookmarkRelationId> {
    Optional<SpaceBookmarkRelation> findSpaceBookmarkRelationBySpaceBookmarkRelationId(
            SpaceBookmarkRelationId spaceBookmarkRelationId);

}