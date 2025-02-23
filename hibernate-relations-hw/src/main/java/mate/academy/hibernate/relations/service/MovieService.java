package mate.academy.hibernate.relations.service;

import mate.academy.hibernate.relations.model.Movie;

public interface MovieService {
    Movie add(Movie movie);

    Movie get(Long id);
}
