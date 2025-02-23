package mate.academy.liquibase.dao;

import java.util.Optional;
import mate.academy.liquibase.model.Country;

public interface CountryDao {
    Country save(Country country);

    Optional<Country> findById(Long id);
}
