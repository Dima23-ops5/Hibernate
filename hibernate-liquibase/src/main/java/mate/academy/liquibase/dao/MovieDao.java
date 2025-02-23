package mate.academy.liquibase.dao;

import java.util.Optional;
import mate.academy.liquibase.model.Movie;

public interface MovieDao {
    Movie save(Movie movie);

    Optional<Movie> findById(Long id);
}
