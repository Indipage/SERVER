package indipage.org.indipage.domain.Relation;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class SpaceTagRelationId implements Serializable {


    @Column(name = "space_id", nullable = false)
    private UUID spaceId;

    @Column(name = "tag_id", nullable = false)
    private Long tagId;
}
