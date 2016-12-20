package com.tejatummalapalli.analyzeCountires.dao;

import com.tejatummalapalli.analyzeCountries.model.Country;

import java.util.List;

public interface CountryDao {
     List<Country> getAllCountries();
     boolean addCountry(Country country) throws  org.hibernate.exception.ConstraintViolationException;
     boolean deleteCountry(String countryName);
     Country getMinMaxStat(String condition , String column);
     Country getCountry(String countryName);
     double getCorrelationCoefficient();
     void updateCountry(String countryID,String toBeUpdatedColumn,String newValue);
}
