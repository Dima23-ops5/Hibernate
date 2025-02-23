package mate.academy.liquibase.service.impl;

import mate.academy.liquibase.dao.ActorDao;
import mate.academy.liquibase.exception.DataProcessingException;
import mate.academy.liquibase.model.Actor;
import mate.academy.liquibase.service.ActorService;

public class ActorServiceImpl implements ActorService {
    private final ActorDao actorDao;

    public ActorServiceImpl(ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    @Override
    public Actor save(Actor actor) {
        return actorDao.save(actor);
    }

    @Override
    public Actor get(Long id) {
        return actorDao.findById(id)
                .orElseThrow(DataProcessingException.findByIdSupplier(id, Actor.class));
    }
}
