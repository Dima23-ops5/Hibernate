package core.basesyntax.dao.ma;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.ma.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class MentorDaoImplTest extends AbstractTest {
    private MentorDao mentorDao;

    @Override
    protected Class<?>[] entities() {
        return new Class[] {
                Person.class,
                Coach.class,
                Mentor.class
        };
    }

    @Before
    public void setUp() {
        mentorDao = new MentorDaoImpl(getSessionFactory());
    }

    @Test
    public void findByAge_Ok() {
        Mentor firstMentor = new Mentor();
        firstMentor.setName("FirstMentor");
        firstMentor.setAge(23);
        Mentor secondMentor = new Mentor();
        secondMentor.setName("SecondMentor");
        secondMentor.setAge(25);
        Mentor thirdMentor = new Mentor();
        thirdMentor.setName("FirstMentor");
        thirdMentor.setAge(18);
        mentorDao.save(firstMentor);
        mentorDao.save(secondMentor);
        mentorDao.save(thirdMentor);

        Assert.assertNotNull(firstMentor.getId());
        Assert.assertNotNull(secondMentor.getId());
        Assert.assertNotNull(thirdMentor.getId());

        List<Mentor> byAgeGreaterThan = mentorDao.findByAgeGreaterThan(20);
        Assert.assertEquals(2, byAgeGreaterThan.size());
    }
}
