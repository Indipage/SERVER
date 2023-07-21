package indipage.org.indipage.domain;

import indipage.org.indipage.domain.Relation.BookRecommendationRelation;
import indipage.org.indipage.domain.Relation.SpaceTagRelation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String imageUrl;

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

    @Column
    @Embedded
    private Address address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "space")
    private List<BookRecommendationRelation> bookRecommendationRelations = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "space")
    private List<SpaceTagRelation> spaceTagRelations = new ArrayList<>();
}
