package indipage.org.indipage.domain;

import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface SpaceRepository extends Repository<Space, Long> {
    Optional<Space> findById(Long id);
}
