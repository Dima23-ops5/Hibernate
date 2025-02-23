package mate.academy.hibernate.relations.service;

import mate.academy.hibernate.relations.model.Actor;

public interface ActorService {
    Actor add(Actor actor);

    Actor get(Long id);
}
