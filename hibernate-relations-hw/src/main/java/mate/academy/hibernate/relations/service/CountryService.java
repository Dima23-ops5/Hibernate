package mate.academy.hibernate.relations.service;

import mate.academy.hibernate.relations.model.Country;

public interface CountryService {
    Country add(Country country);

    Country get(Long id);
}
