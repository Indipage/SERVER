package indipage.org.indipage.domain.Relation;

import indipage.org.indipage.domain.Space;
import indipage.org.indipage.domain.Tag;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SpaceTagRelation {

    @EmbeddedId
    SpaceTagRelationId spaceTagRelationId;

    @ManyToOne
    @MapsId("spaceId")
    @JoinColumn(name = "space_id")
    private Space space;

    @ManyToOne
    @MapsId("tagId")
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
