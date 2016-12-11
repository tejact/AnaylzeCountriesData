import com.tejatummalapalli.analyzeCountires.dao.CountryDao;
import com.tejatummalapalli.analyzeCountires.dao.SimpleCountryDaoImpl;

import java.util.List;

public class Main {

    public static void main(String args[]) {
        System.out.println("Hello..Welcome to Hibernate");


/*

        Country dummyCountry = new Country.CountryBuilder()
                                    .withName("India2")
                                    .withAdultLiteracyRate(90l)
                                    .withAdultLiteracyRate(80l)
                                    .build();
*/

        CountryDao countryDao = new SimpleCountryDaoImpl();
        List allCountries = countryDao.getAllCountries();
        allCountries.stream()
                    .forEach(System.out::println);

    }

}

