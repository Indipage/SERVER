package indipage.org.indipage.domain;

import indipage.org.indipage.domain.Relation.BookRecommendationRelation;
import indipage.org.indipage.domain.Relation.SpaceTagRelation;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
public class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    private String roadAddress;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SpaceType type;

    @Column
    private String owner;

    @Column
    private String operatingTime;

    @Column
    private String closedDays;

    @Column
    private String introduction;

    @Column
    private String peculiarityTitle;

    @Column
    private String peculiarityContent;

    @Column
    private String peculiarityImageUrl;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "space")
    private List<BookRecommendationRelation> bookRecommendationRelations = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "space")
    private List<SpaceTagRelation> spaceTagRelations = new ArrayList<>();
}
