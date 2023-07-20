package indipage.org.indipage.domain;

import indipage.org.indipage.domain.Relation.SpaceBookmarkRelation;
import indipage.org.indipage.domain.Relation.SpaceBookmarkRelationId;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface SpaceBookmarkRelationRepository extends
        Repository<SpaceBookmarkRelation, SpaceBookmarkRelationId> {
    Optional<SpaceBookmarkRelation> findSpaceBookmarkRelationBySpaceBookmarkRelationId(
            SpaceBookmarkRelationId spaceBookmarkRelationId);

    void save(SpaceBookmarkRelation relation);

    void delete(SpaceBookmarkRelation relation);

    List<SpaceBookmarkRelation> findAllByUserOrderByCreatedAtDesc(User user);
}