package mate.academy.liquibase.service;

import mate.academy.liquibase.model.Actor;

public interface ActorService {
    Actor save(Actor actor);

    Actor get(Long id);
}
