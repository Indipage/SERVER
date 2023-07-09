package indipage.org.indipage.domain;

import indipage.org.indipage.domain.Relation.ArticleBookmarkRelation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends CreatedTimeBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Space space;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ArticleBookmarkRelation> articleBookmarkRelations = new ArrayList<>();
}
