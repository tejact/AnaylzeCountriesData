import com.tejatummalapalli.analyzeCountires.dao.CountryDao;
import com.tejatummalapalli.analyzeCountires.dao.SimpleCountryDaoImpl;
import com.tejatummalapalli.analyzeCountries.model.Country;

import java.util.List;

public class Main {

    static CountryDao countryDao = new SimpleCountryDaoImpl();

    public static void main(String args[]) {
        System.out.println("Hello..Welcome to Hibernate");


        //addCountry();
        //deleteCountry();


        //displayFormattedData();

        System.out.println(countryDao.getMinMaxStat("Minimum","Literacy"));
        System.out.println(countryDao.getMinMaxStat("Maximum","Literacy"));
        System.out.println(countryDao.getMinMaxStat("Minimum","InternetUsers"));
        System.out.println(countryDao.getMinMaxStat("Maximum","InternetUsers"));


    }

    private static void deleteCountry() {
        //countryDao.deleteCountry(country);
    }

    private static void addCountry() {
        Country dummyCountry = new Country.CountryBuilder()
                                    .withName("AndhraIndia2")
                                    .withCode("AND")
                                    .withInternetUsers(90.00)
                                    .withAdultLiteracyRate(80.99)
                                    .build();
        countryDao.addCountry(dummyCountry);
    }

    private static void displayFormattedData(){
        List<Country> allCountries = countryDao.getAllCountries();

      /*
      //Display all the coutries.. toString on country is used.
      allCountries.stream()
                .forEach(System.out::println);*/


      //Print Headers
        System.out.println("Country                             Internet Users          Literacy");
        System.out.println("--------------------------------------------------------------------");

      //Print Data
        for( Country country : allCountries) {
            Double internetUsers = country.getInternetUsers();
            Double adultLiteracyRate = country.getAdultLiteracyRate();

            String formatedInternetUsers = formatDouble(internetUsers);
            String formatedAdultLiteracyRate = formatDouble(adultLiteracyRate);

            System.out.println(country.getName()+"****"+formatedInternetUsers+"***"+formatedAdultLiteracyRate);
      }
    }

    private static String formatDouble(Double rawMetric) {
        String formatedMetric;
        if(rawMetric != null) {
            formatedMetric = String.format("%.2f", rawMetric);
        } else {
            formatedMetric = "--";
        }
        return formatedMetric;
    }

}

