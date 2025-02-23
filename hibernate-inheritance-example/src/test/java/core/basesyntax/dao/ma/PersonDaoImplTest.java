package core.basesyntax.dao.ma;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.ma.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PersonDaoImplTest extends AbstractTest {
    private PersonDao personDao;
    @Override
    protected Class<?>[] entities() {
        return new Class[] {
                Person.class,
                Coach.class,
                Mentor.class
        };
    }

    @Before
    public void setUp() throws Exception {
        personDao = new PersonDaoImpl(getSessionFactory());
    }

    @Test
    public void createPerson_Ok() {
        Person person = new Person();
        person.setAge(20);
        person.setName("Bob");
        Person actual = personDao.save(person);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createCoach_Ok() {
        Coach coach = new Coach();
        coach.setAge(20);
        coach.setName("John");
        Person actual = personDao.save(coach);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createMentor_Ok() {
        Mentor mentor = new Mentor();
        mentor.setAge(20);
        mentor.setName("Alice");
        Person actual = personDao.save(mentor);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }
}
