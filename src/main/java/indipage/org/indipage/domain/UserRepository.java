package indipage.org.indipage.domain;

import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {
    Optional<User> findById(Long id);

    Optional<User> findByEmailAndName(String email, String name);

    User save(User user);
}
