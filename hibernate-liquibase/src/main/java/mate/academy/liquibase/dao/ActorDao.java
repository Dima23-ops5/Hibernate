package mate.academy.liquibase.dao;

import java.util.Optional;
import mate.academy.liquibase.model.Actor;

public interface ActorDao {
    Actor save(Actor actor);

    Optional<Actor> findById(Long id);
}
