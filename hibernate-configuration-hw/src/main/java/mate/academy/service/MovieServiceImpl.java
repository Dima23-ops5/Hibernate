package mate.academy.service;

import mate.academy.dao.MovieDaoImpl;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.Movie;

import java.util.NoSuchElementException;

@Service
public class MovieServiceImpl implements MovieService {
    @Inject
    private MovieDaoImpl movieDao;

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        return movieDao.get(id).orElseThrow(() ->
                new NoSuchElementException("Did not find movie with id:" + id));
    }
}
