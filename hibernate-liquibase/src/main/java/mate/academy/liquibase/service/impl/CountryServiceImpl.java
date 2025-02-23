package mate.academy.liquibase.service.impl;

import mate.academy.liquibase.dao.CountryDao;
import mate.academy.liquibase.exception.DataProcessingException;
import mate.academy.liquibase.model.Country;
import mate.academy.liquibase.service.CountryService;

public class CountryServiceImpl implements CountryService {
    private final CountryDao countryDao;

    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public Country save(Country country) {
        return countryDao.save(country);
    }

    @Override
    public Country get(Long id) {
        return countryDao.findById(id)
                .orElseThrow(DataProcessingException.findByIdSupplier(id, Country.class));
    }
}
