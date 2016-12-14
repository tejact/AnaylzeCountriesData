package com.tejatummalapalli.analyzeCountires.dao;

import com.tejatummalapalli.analyzeCountries.model.Country;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

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



    @Test
    public void adultLiteracyRateShouldBeAcurateWithNoNull() throws Exception {
        //Arrange
        //Dummy countries are created. Note all the 3 countries has values and no nulls.

        //Act
        double adultLiteracyMean =  simpleCountryDao.getAdultLiteracyMean(allCountries);

        //Assert
        assertEquals(45.66,adultLiteracyMean,0.5);
    }

    @Test
    public void adultLiteracyRateShouldBeAcurateWithAdultLiteracyRateAsNull() throws Exception {
        //Arrange
        //Add forth country with adult Literacy rate as null
        Country dummyCountry4 = new Country.CountryBuilder()
                .withName("KARNATAKA")
                .withCode("KTK")
                .withInternetUsers(90.00)
                .withAdultLiteracyRate(null)
                .build();
        allCountries.add(dummyCountry4);


        //Act
        double adultLiteracyMean =  simpleCountryDao.getAdultLiteracyMean(allCountries);

        //Assert
        assertEquals(45.66,adultLiteracyMean,0.5);
    }

    @Test
    public void adultLiteracyRateShouldBeAcurateWithInternetUsersAsNull() throws Exception {
        //Arrange
        //Add forth country with adult Literacy rate as null
        Country dummyCountry4 = new Country.CountryBuilder()
                .withName("KARNATAKA")
                .withCode("KTK")
                .withInternetUsers(null)
                .withAdultLiteracyRate(90.00)
                .build();
        allCountries.add(dummyCountry4);


        //Act
        double adultLiteracyMean =  simpleCountryDao.getAdultLiteracyMean(allCountries);

        //Assert
        assertEquals(45.66,adultLiteracyMean,0.5);
    }

    @Test
    public void adultLiteracyRateShouldBeAcurateWithBothValuesAsNull() throws Exception {
        //Arrange
        //Add forth country with adult Literacy rate as null
        Country dummyCountry4 = new Country.CountryBuilder()
                .withName("KARNATAKA")
                .withCode("KTK")
                .withInternetUsers(null)
                .withAdultLiteracyRate(null)
                .build();
        allCountries.add(dummyCountry4);


        //Act
        double adultLiteracyMean =  simpleCountryDao.getAdultLiteracyMean(allCountries);

        //Assert
        assertEquals(45.66,adultLiteracyMean,0.5);
    }

    @Test
    public void adultLiteracyRateShouldBeAcurateWithRandomNull() throws Exception {
        //Arrange
        //Add forth country with adult Literacy rate as null
        Country dummyCountry4 = new Country.CountryBuilder()
                .withName("KARNATAKA")
                .withCode("KTK")
                .withInternetUsers(null)
                .withAdultLiteracyRate(null)
                .build();
        Country dummyCountry5 = new Country.CountryBuilder()
                .withName("KARNATAKA")
                .withCode("KTK")
                .withInternetUsers(90.00)
                .withAdultLiteracyRate(null)
                .build();

        Country dummyCountry6 = new Country.CountryBuilder()
                .withName("KARNATAKA")
                .withCode("KTK")
                .withInternetUsers(null)
                .withAdultLiteracyRate(80.00)
                .build();

        allCountries.add(dummyCountry4);


        //Act
        double adultLiteracyMean =  simpleCountryDao.getAdultLiteracyMean(allCountries);

        //Assert
        assertEquals(45.66,adultLiteracyMean,0.5);
    }

}