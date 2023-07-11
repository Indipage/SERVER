package indipage.org.indipage.domain;

import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface TicketRepository extends Repository<Ticket, Long> {
    Optional<Ticket> findBySpace(Space space);
}
