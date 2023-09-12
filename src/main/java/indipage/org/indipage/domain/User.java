package indipage.org.indipage.domain;

import indipage.org.indipage.auth.Platform;
import indipage.org.indipage.domain.Relation.ArticleBookmarkRelation;
import indipage.org.indipage.domain.Relation.SpaceBookmarkRelation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @Column
    @Enumerated(value = EnumType.STRING)
    private Platform platform;

    @Column
    private LocalDateTime slideAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<ArticleBookmarkRelation> articleBookmarkRelations = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<SpaceBookmarkRelation> spaceBookmarkRelations = new ArrayList<>();

    public void updateSlideAt() {
        this.slideAt = LocalDateTime.now();
    }

    private User(String email, String name, Platform platform) {
        this.email = email;
        this.name = name;
        this.platform = platform;
    }

    public static User of(String email, String name, Platform platform) {
        return new User(email, name, platform);
    }
}
