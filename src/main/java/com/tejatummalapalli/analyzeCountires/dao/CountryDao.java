package com.tejatummalapalli.analyzeCountires.dao;

import com.tejatummalapalli.analyzeCountries.model.Country;

import java.util.List;

public interface CountryDao {
    public List<Country> getAllCountries();
    public boolean addCountry(Country country);
    public boolean deleteCountry(Country country);
}
