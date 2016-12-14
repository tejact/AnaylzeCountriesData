package com.tejatummalapalli.analyzeCountires.dao;

import com.tejatummalapalli.analyzeCountries.model.Country;

import java.util.List;

public interface CountryDao {
     List<Country> getAllCountries();
     boolean addCountry(Country country) throws  org.hibernate.exception.ConstraintViolationException;
     boolean deleteCountry(Country country);
     Country getMinMaxStat(String condition , String column);
     double getCorrelationCoefficient();
     double getAdultLiteracyMean(List<Country> allCountries);
     double getInternetUsersMean(List<Country> allCountries) ;
}
