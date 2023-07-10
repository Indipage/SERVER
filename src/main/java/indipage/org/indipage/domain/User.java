package indipage.org.indipage.domain;

import indipage.org.indipage.domain.Relation.ArticleBookmarkRelation;
import indipage.org.indipage.domain.Relation.SpaceBookmarkRelation;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends CreatedTimeBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<ArticleBookmarkRelation> articleBookmarkRelations = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<SpaceBookmarkRelation> spaceBookmarkRelations = new ArrayList<>();
}
