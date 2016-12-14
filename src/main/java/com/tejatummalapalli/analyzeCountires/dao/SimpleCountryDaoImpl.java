package com.tejatummalapalli.analyzeCountires.dao;

import com.tejatummalapalli.analyzeCountries.model.Country;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.Comparator;
import java.util.List;

public class SimpleCountryDaoImpl implements CountryDao {

    //TODO:Make these constants available in ENUM. Every other class can access them
    public static final String MAXIMUM = "Maximum";
    public static final String LITERACY = "Literacy";
    public static final String MINIMUM = "Minimum";
    public static final String INTERNET_USERS = "InternetUsers";
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
                                                    .get();


        } else if( condition.equals(MINIMUM) && column.equals(LITERACY)) {
            countryWithDesiredStatistic = countries.stream()
                                                    .filter(c1 -> c1.getAdultLiteracyRate() != null)
                                                    .min(literacyComparator)
                                                    .get();

        }
        else if( condition.equals(MAXIMUM) && column.equals(INTERNET_USERS) ) {
            countryWithDesiredStatistic = countries.stream()
                    .filter(c1 -> c1.getInternetUsers() != null)
                    .max(internetUsersComparator)
                    .get();
        } else if( condition.equals(MINIMUM) && column.equals(INTERNET_USERS) ) {
            countryWithDesiredStatistic = countries.stream()
                    .filter(c1 -> c1.getInternetUsers() != null)
                    .min(internetUsersComparator)
                    .get();
        }


        session.close();
        return countryWithDesiredStatistic;
    }

    @Override
    public double getCorrelationCoefficient() {
        double correlationCoefficient = 0;
        List<Country> allCountries;
        allCountries = getAllCountries();

        double internetUsersMean = getInternetUsersMean(allCountries);

        System.out.println("The mean of internet users is "+internetUsersMean);


        double adultLiteracyMean = getAdultLiteracyMean(allCountries);

        System.out.println("The mean of adult Literacy rate is "+adultLiteracyMean);

        double diffAdultLiteracyWithMean;
        double diffInternetUsageWithMean;
        double productOfTwoMeans;
        double sumOfProductOfTwoDiffMeans = 0;
        double sumAdultLiteracySquare = 0;
        double sumIntenetUsersSquare = 0;

        for(Country country : allCountries) {


            final Double adultLiteracyRate = country.getAdultLiteracyRate();
            final Double internetUsers = country.getInternetUsers();
            if(adultLiteracyRate != null && internetUsers != null) {
                    diffAdultLiteracyWithMean = adultLiteracyRate - adultLiteracyMean;
                    diffInternetUsageWithMean = internetUsers - internetUsersMean;
                    productOfTwoMeans = diffAdultLiteracyWithMean * diffInternetUsageWithMean;

                    sumOfProductOfTwoDiffMeans += productOfTwoMeans;
                    sumAdultLiteracySquare += (adultLiteracyRate * 2);
                    sumIntenetUsersSquare += (adultLiteracyRate * 2);

            }

        }

        correlationCoefficient = (sumOfProductOfTwoDiffMeans) / Math.sqrt(sumAdultLiteracySquare*sumIntenetUsersSquare);
        return correlationCoefficient;
    }

     public double getAdultLiteracyMean(List<Country> allCountries) {
        return allCountries.stream()
                                                    .filter(country -> (country.getAdultLiteracyRate() != null  && country.getInternetUsers() != null))
                                                    .mapToDouble(Country::getAdultLiteracyRate)
                                                    .average()
                                                    .orElseThrow(IllegalArgumentException::new);
    }

     public double getInternetUsersMean(List<Country> allCountries) {
        return allCountries.stream()
                                                    .filter(country -> (country.getAdultLiteracyRate() != null  && country.getInternetUsers() != null))
                                                    .mapToDouble(Country::getInternetUsers)
                                                    .average()
                                                    .orElseThrow(IllegalArgumentException::new);
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
    public boolean deleteCountry(Country country) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(country);
        session.getTransaction().commit();
        session.close();
        return true;
    }
}
