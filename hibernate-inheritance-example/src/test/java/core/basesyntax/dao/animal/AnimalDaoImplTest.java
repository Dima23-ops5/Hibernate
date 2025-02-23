package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class AnimalDaoImplTest extends AbstractTest {
    private AnimalDao animalDao;

    @Override
    protected Class<?>[] entities() {
        return new Class[]{
                Animal.class,
                Cat.class,
                Dog.class
        };
    }

    @Before
    public void setUp() {
        animalDao = new AnimalDaoImpl(getSessionFactory());
    }

    @Test
    public void createAnimal_Ok() {
        Animal animal = new Animal();
        animal.setName("Animal");
        animal.setAge(3);
        Animal actualAnimal = animalDao.save(animal);
        Assert.assertNotNull(actualAnimal);
        Assert.assertEquals(1L, actualAnimal.getId().longValue());
    }

    @Test
    public void createCat_Ok() {
        Cat cat = new Cat();
        cat.setName("Barsik");
        cat.setColor("Black");
        cat.setNumberOfLives(9);
        cat.setAge(1);
        Animal actualCat = animalDao.save(cat);
        Assert.assertNotNull(actualCat);
        Assert.assertEquals(1L, actualCat.getId().longValue());
    }

    @Test
    public void createDog_Ok() {
        Dog dog = new Dog();
        dog.setName("Bob");
        dog.setAge(3);
        dog.setTailLength(1);
        dog.setOwner("Alice");
        Animal actualDog = animalDao.save(dog);
        Assert.assertNotNull(actualDog);
        Assert.assertEquals(1L, actualDog.getId().longValue());
    }

    @Test
    public void findByNameFirstLetter_Ok() {
        Dog bob = new Dog();
        bob.setName("Bob");
        Dog buddy = new Dog();
        buddy.setName("buddy");
        Cat fluffy = new Cat();
        fluffy.setName("Fluffy");
        Animal bull = new Animal();
        bull.setName("Bull");
        animalDao.save(bob);
        animalDao.save(buddy);
        animalDao.save(bull);
        animalDao.save(fluffy);
        List<Animal> animalsByNameFirstLetter = animalDao.findByNameFirstLetter('B');
        Assert.assertNotNull(animalsByNameFirstLetter);
        Assert.assertEquals(3, animalsByNameFirstLetter.size());
    }

    @Test
    public void findByNameFirstLetter_ZeroAnimals() {
        Dog rob = new Dog();
        rob.setName("Rob");
        Dog uddy = new Dog();
        uddy.setName("Uddy");
        Cat fluffy = new Cat();
        fluffy.setName("Fluffy");
        Animal doll = new Animal();
        doll.setName("Doll");
        animalDao.save(rob);
        animalDao.save(uddy);
        animalDao.save(doll);
        animalDao.save(fluffy);
        List<Animal> animalsByNameFirstLetter = animalDao.findByNameFirstLetter('B');
        Assert.assertNotNull(animalsByNameFirstLetter);
        Assert.assertEquals(0, animalsByNameFirstLetter.size());
    }
}
