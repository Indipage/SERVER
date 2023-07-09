package indipage.org.indipage.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ticketImageUrl;

    @Column(nullable = false)
    private String cardImageUrl;

    @ManyToOne
    @JoinColumn(name = "space_id")
    private Space space;
}
