package indipage.org.indipage.domain;

import indipage.org.indipage.domain.Relation.InviteSpaceRelation;
import indipage.org.indipage.domain.Relation.InviteSpaceRelationId;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface InviteSpaceRelationRepository extends Repository<InviteSpaceRelation, InviteSpaceRelationId> {
    Optional<InviteSpaceRelation> findByInviteSpaceRelationId(InviteSpaceRelationId id);
}