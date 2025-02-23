package mate.academy.liquibase.service;

import mate.academy.liquibase.model.Country;

public interface CountryService {
    Country save(Country country);

    Country get(Long id);
}
