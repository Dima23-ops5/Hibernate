package mate.academy.liquibase.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.liquibase.dao.AbstractTest;
import mate.academy.liquibase.dao.ActorDao;
import mate.academy.liquibase.dao.CountryDao;
import mate.academy.liquibase.dao.MovieDao;
import mate.academy.liquibase.model.Actor;
import mate.academy.liquibase.model.Country;
import mate.academy.liquibase.model.Movie;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MovieDaoImplTest extends AbstractTest {
    private static final Movie shawshankRedemption = new Movie("The Shawshank Redemption");
    private static final Actor morganFreeman = new Actor("Morgan Freeman");
    private static final Country usa = new Country("USA");

    @Override
    protected Class<?>[] entities() {
        return new Class[]{Movie.class, Actor.class, Country.class};
    }

    @Before
    public void setUp() throws Exception {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.createNativeQuery("DELETE FROM movies_actors;").executeUpdate();
                session.createNativeQuery("DELETE FROM movies;", Movie.class).executeUpdate();
                session.createNativeQuery("DELETE FROM actors;", Actor.class).executeUpdate();
                session.createNativeQuery("ALTER TABLE movies ALTER COLUMN id RESTART WITH 0;", Movie.class).executeUpdate();
                session.createNativeQuery("ALTER TABLE actors ALTER COLUMN id RESTART WITH 0;", Actor.class).executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw e;
            }
        }
    }

    @Test
    public void create_Ok() {
        MovieDao movieDao = new MovieDaoImpl(getSessionFactory());
        verifyCreateMovieWorks(movieDao, shawshankRedemption.clone(), 0L);
    }

    @Test
    public void getById_Ok() {
        MovieDao movieDao = new MovieDaoImpl(getSessionFactory());
        verifyCreateMovieWorks(movieDao, shawshankRedemption.clone(), 0L);
        Optional<Movie> actualOptional = movieDao.findById(0L);
        Assert.assertTrue(actualOptional.isPresent());
        Movie actual = actualOptional.get();
        Assert.assertNotNull(actual);
        Assert.assertEquals(0L, actual.getId().longValue());
        Assert.assertEquals(shawshankRedemption.getTitle(), actual.getTitle());
    }

    @Test
    public void saveWithActor_ok() {
        Actor morganFreemanClone = morganFreeman.clone();
        ActorDao actorDao = new ActorDaoImpl(getSessionFactory());
        ActorDaoImplTest.verifyCreateActorWorks(actorDao, morganFreemanClone, 0L);

        MovieDao movieDao = new MovieDaoImpl(getSessionFactory());
        Movie shawshankRedemptionWithActor = shawshankRedemption.clone();
        shawshankRedemptionWithActor.setActors(List.of(morganFreemanClone));
        verifyCreateMovieWorks(movieDao, shawshankRedemptionWithActor, 0L);

        Optional<Movie> actualOptional = movieDao.findById(0L);
        Assert.assertTrue(actualOptional.isPresent());
        Movie actual = actualOptional.get();
        Assert.assertNotNull(actual);
        Assert.assertEquals(0L, actual.getId().longValue());
        Assert.assertEquals(shawshankRedemption.getTitle(), actual.getTitle());
        Assert.assertNotNull(actual.getActors());
        Assert.assertEquals(1, actual.getActors().size());
        Assert.assertEquals(morganFreeman.getName(), actual.getActors().get(0).getName());
    }

    @Test
    public void saveWithActorAndCountry_ok() {
        Country usaClone = usa.clone();
        CountryDao countryDao = new CountryDaoImpl(getSessionFactory());
        CountryDaoImplTest.verifyCreateCountryWorks(countryDao, usaClone, 0L);

        Actor morganFreemanClone = morganFreeman.clone();
        morganFreemanClone.setCountry(usaClone);
        ActorDao actorDao = new ActorDaoImpl(getSessionFactory());
        ActorDaoImplTest.verifyCreateActorWorks(actorDao, morganFreemanClone, 0L);

        MovieDao movieDao = new MovieDaoImpl(getSessionFactory());
        Movie shawshankRedemptionWithActor = shawshankRedemption.clone();
        shawshankRedemptionWithActor.setActors(List.of(morganFreemanClone));
        verifyCreateMovieWorks(movieDao, shawshankRedemptionWithActor, 0L);

        Optional<Movie> actualOptional = movieDao.findById(0L);
        Assert.assertTrue(actualOptional.isPresent());
        Movie actual = actualOptional.get();
        Assert.assertNotNull(actual);
        Assert.assertEquals(0L, actual.getId().longValue());
        Assert.assertEquals(shawshankRedemption.getTitle(), actual.getTitle());
        Assert.assertNotNull(actual.getActors());
        Assert.assertEquals(1, actual.getActors().size());
        Assert.assertEquals(morganFreeman.getName(), actual.getActors().get(0).getName());
        Assert.assertNotNull(actual.getActors().get(0).getCountry());
        Assert.assertEquals(0, actual.getActors().get(0).getCountry().getId().longValue());
        Assert.assertEquals(usa.getName(), actual.getActors().get(0).getCountry().getName());
    }

    @Test
    public void getByNotExistingId_Ok() {
        MovieDao movieDao = new MovieDaoImpl(getSessionFactory());
        Optional<Movie> actual = movieDao.findById(100L);
        Assert.assertFalse(actual.isPresent());
    }

    private void verifyCreateMovieWorks(MovieDao movieDao, Movie movie, Long expectedId) {
        Movie actual = movieDao.save(movie);
        Assert.assertNotNull("Check you have implemented the `create` method " +
                "in the MovieDaoImpl class", actual);
        Assert.assertNotNull("ID for movie should be autogenerated", actual.getId());
        Assert.assertEquals(expectedId, actual.getId());
        Assert.assertEquals(movie.getTitle(), actual.getTitle());
    }
}
