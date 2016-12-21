package com.tejatummalapalli.analyzeCountries.dao;

import com.tejatummalapalli.analyzeCountries.exceptions.CountryNotFoundException;
import com.tejatummalapalli.analyzeCountries.model.Country;

import java.util.List;

public interface CountryDao {
     List<Country> getAllCountries();
     boolean addCountry(Country country) throws  org.hibernate.exception.ConstraintViolationException;
     boolean deleteCountry(String countryName);
     Country getMinMaxStat(String condition , String column);
     Country getCountry(String countryName) throws CountryNotFoundException;
     double getCorrelationCoefficient();
     void updateCountry(String countryID,String toBeUpdatedColumn,String newValue) throws CountryNotFoundException;
}
