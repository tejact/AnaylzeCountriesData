package com.tejatummalapalli.analyzeCountries.dao;

import com.tejatummalapalli.analyzeCountries.model.Country;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Teja on 12/13/2016.
 */
public class SimpleCountryDaoImplTest {

    public List<Country> allCountries;
    CountryDao simpleCountryDao;

    @Before
    public void setUp() {
        simpleCountryDao = new SimpleCountryDaoImpl();
        allCountries = new ArrayList<>();

        Country dummyCountry1 = new Country.CountryBuilder()
                .withName("TAMIL NADU")
                .withCode("TNL")
                .withInternetUsers(90.00)
                .withAdultLiteracyRate(80.99)
                .build();

        Country dummyCountry2 = new Country.CountryBuilder()
                .withName("Andhra")
                .withCode("AND")
                .withInternetUsers(6.53)
                .withAdultLiteracyRate(5.09)
                .build();

        Country dummyCountry3 = new Country.CountryBuilder()
                .withName("Telagana")
                .withCode("TEL")
                .withInternetUsers(65.3)
                .withAdultLiteracyRate(50.9)
                .build();

        allCountries.add(dummyCountry1);
        allCountries.add(dummyCountry2);
        allCountries.add(dummyCountry3);
    }
}