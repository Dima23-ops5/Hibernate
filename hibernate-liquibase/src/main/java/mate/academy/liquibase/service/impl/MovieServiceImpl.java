package mate.academy.liquibase.service.impl;

import mate.academy.liquibase.dao.MovieDao;
import mate.academy.liquibase.exception.DataProcessingException;
import mate.academy.liquibase.model.Movie;
import mate.academy.liquibase.service.MovieService;

public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie save(Movie movie) {
        return movieDao.save(movie);
    }

    @Override
    public Movie get(Long id) {
        return movieDao.findById(id)
                .orElseThrow(DataProcessingException.findByIdSupplier(id, Movie.class));
    }
}
