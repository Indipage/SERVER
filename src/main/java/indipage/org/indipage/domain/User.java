package indipage.org.indipage.domain;

import indipage.org.indipage.domain.Relation.ArticleBookmarkRelation;
import indipage.org.indipage.domain.Relation.FollowSpaceRelation;
import indipage.org.indipage.domain.Relation.InviteSpaceRelation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @OneToMany(fetch = FetchType.LAZY)
    private List<ArticleBookmarkRelation> articleBookmarkRelations = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<FollowSpaceRelation> followSpaceRelations = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<InviteSpaceRelation> inviteSpaceRelations = new ArrayList<>();
}
