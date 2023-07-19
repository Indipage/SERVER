package indipage.org.indipage.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SpaceRepository extends Repository<Space, Long> {
    Optional<Space> findById(Long id);

    @Query(value = "SELECT * FROM space s WHERE MATCH(s.base_government, s.city, s.metro_government, s.road_name, s.town, s.detail) AGAINST(:searchTerm)", nativeQuery = true)
    List<Space> searchByAddress(@Param("searchTerm") String searchTerm);

    List<Space> findAll();
}
