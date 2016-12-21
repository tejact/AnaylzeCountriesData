package com.tejatummalapalli.analyzeCountries.dao;

import com.tejatummalapalli.analyzeCountries.exceptions.CountryNotFoundException;
import com.tejatummalapalli.analyzeCountries.model.Country;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.Query;
import java.util.Comparator;
import java.util.List;

public class SimpleCountryDaoImpl implements CountryDao {

    //TODO:Make these constants available in ENUM. Every other class can access them
    private static final String MAXIMUM = "Maximum";
    private static final String LITERACY = "Literacy";
    private static final String MINIMUM = "Minimum";
    private static final String INTERNET_USERS = "InternetUsers";
    private final SessionFactory sessionFactory = buildSessionFactory();

    private SessionFactory buildSessionFactory() {
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Override
    public List<Country> getAllCountries() {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Country.class);
        List<Country> countries = criteria.list();
        session.close();
        return countries;
    }



    public Country getMinMaxStat(String condition , String column) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Country.class);
        List<Country> countries = criteria.list();
        Country countryWithDesiredStatistic = null;

        Comparator<Country> literacyComparator = (c1,c2)  -> c1.getAdultLiteracyRate().compareTo(c2.getAdultLiteracyRate());
        Comparator<Country> internetUsersComparator = (c1,c2)  -> c1.getInternetUsers().compareTo(c2.getInternetUsers());

        if(condition.equals(MAXIMUM) && column.equals(LITERACY)) {
            countryWithDesiredStatistic =  countries.stream()
                                                    .filter(c1 -> c1.getAdultLiteracyRate() != null)
                                                    .max(literacyComparator)
                                                    .orElse(null);


        } else if( condition.equals(MINIMUM) && column.equals(LITERACY)) {
            countryWithDesiredStatistic = countries.stream()
                                                    .filter(c1 -> c1.getAdultLiteracyRate() != null)
                                                    .min(literacyComparator)
                                                    .orElse(null);

        }
        else if( condition.equals(MAXIMUM) && column.equals(INTERNET_USERS) ) {
            countryWithDesiredStatistic = countries.stream()
                    .filter(c1 -> c1.getInternetUsers() != null)
                    .max(internetUsersComparator)
                    .orElse(null);
        } else if( condition.equals(MINIMUM) && column.equals(INTERNET_USERS) ) {
            countryWithDesiredStatistic = countries.stream()
                    .filter(c1 -> c1.getInternetUsers() != null)
                    .min(internetUsersComparator)
                    .orElse(null);
        }


        session.close();
        return countryWithDesiredStatistic;
    }

    @Override
    public Country getCountry(String countryCode) throws CountryNotFoundException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Country country = session.get(Country.class, countryCode);

        if(country == null) {
            throw new CountryNotFoundException();
        }

        session.close();
        return country;
    }

    @Override
    public double getCorrelationCoefficient() {
        List<Country> allCountries;
        allCountries = getAllCountries();

        double[] adultLiteracyRate = allCountries.stream()
                .filter(country -> (country.getAdultLiteracyRate() != null  && country.getInternetUsers() != null))
                .mapToDouble(Country::getAdultLiteracyRate)
                .toArray();

        double[] internetUsers = allCountries.stream()
                .filter(country -> (country.getAdultLiteracyRate() != null  && country.getInternetUsers() != null))
                .mapToDouble(Country::getInternetUsers)
                .toArray();

        Double correlation = 0.0;
        try {
            correlation = new PearsonsCorrelation()
                    .correlation(adultLiteracyRate,internetUsers);
        } catch (MathIllegalArgumentException iae) {
            iae.printStackTrace();
            System.out.println("Adult literacy rate and internet user arrays" +
                    "are too small for the correlation to be calculated");
        }
        return correlation;
    }


    @Override
    public void updateCountry(String countryID, String toBeUpdatedColumn, String newValue) throws CountryNotFoundException {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        Country country = getCountry(countryID);

        if(toBeUpdatedColumn.equals("1")){
            country.setInternetUsers(Double.parseDouble(newValue));
        }else if(toBeUpdatedColumn.equals("2")) {
            country.setInternetUsers(Double.parseDouble(newValue));
        }

        session.update(country);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public boolean addCountry(Country country) throws org.hibernate.exception.ConstraintViolationException{
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(country);
        session.getTransaction().commit();
        session.close();
        return true;
    }


    @Override
    public boolean deleteCountry(String countryName) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        Query query = session.createQuery("delete  From Country  where name = :name");
        query.setParameter("name",countryName );

        int result = query.executeUpdate();

        if (result > 0) {
            System.out.println("Country is deleted");
        }

        session.getTransaction().commit();
        session.close();
        return true;
    }
}
