package indipage.org.indipage.domain;

import indipage.org.indipage.domain.Relation.ArticleBookmarkRelation;
import indipage.org.indipage.domain.Relation.SpaceBookmarkRelation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column
    private LocalDateTime slideAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<ArticleBookmarkRelation> articleBookmarkRelations = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<SpaceBookmarkRelation> spaceBookmarkRelations = new ArrayList<>();

    public void updateSlideAt() {
        this.slideAt = LocalDateTime.now();
    }
}
