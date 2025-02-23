package core.basesyntax.dao.animal;

import core.basesyntax.model.zoo.Animal;
import java.util.List;

public interface AnimalDao {
    Animal save(Animal animal);

    List<Animal> findByNameFirstLetter(Character character);
}
