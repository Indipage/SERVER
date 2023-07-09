package indipage.org.indipage.domain.Relation;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class SpaceTagRelationId implements Serializable {

    @EmbeddedId
    SpaceTagRelationId spaceTagRelationId;

    @Column(name = "space_id", nullable = false)
    private UUID spaceId;

    @Column(name = "tag_id", nullable = false)
    private Long tagId;
}
