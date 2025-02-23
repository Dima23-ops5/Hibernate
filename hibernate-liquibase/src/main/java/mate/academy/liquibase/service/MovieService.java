package mate.academy.liquibase.service;

import mate.academy.liquibase.model.Movie;

public interface MovieService {
    Movie save(Movie movie);

    Movie get(Long id);
}
