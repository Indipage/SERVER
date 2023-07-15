package indipage.org.indipage.domain;

import indipage.org.indipage.domain.Relation.InviteSpaceRelation;
import indipage.org.indipage.domain.Relation.InviteSpaceRelationId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface InviteSpaceRelationRepository extends Repository<InviteSpaceRelation, InviteSpaceRelationId> {
    Optional<InviteSpaceRelation> findByInviteSpaceRelationId(InviteSpaceRelationId id);

    void save(InviteSpaceRelation relation);

    List<InviteSpaceRelation> findAllByUserAndHasVisitedIsFalseOrderByCreatedAtDesc(User user);

    List<InviteSpaceRelation> findAllByUserAndHasVisitedIsTrueOrderByUpdatedAtDesc(User user);
}