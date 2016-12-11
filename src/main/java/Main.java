import com.tejatummalapalli.analyzeCountires.dao.CountryDao;
import com.tejatummalapalli.analyzeCountires.dao.SimpleCountryDaoImpl;
import com.tejatummalapalli.analyzeCountries.model.Country;

import java.util.List;

public class Main {

    static CountryDao countryDao = new SimpleCountryDaoImpl();

    public static void main(String args[]) {
        System.out.println("Hello..Welcome to Hibernate");
/*

        Country dummyCountry = new Country.CountryBuilder()
                                    .withName("India2")
                                    .withAdultLiteracyRate(90l)
                                    .withAdultLiteracyRate(80l)
                                    .build();
*/

        displayFormattedData();

    }

    private static void displayFormattedData(){
        List<Country> allCountries = countryDao.getAllCountries();

      /*  allCountries.stream()
                .forEach(System.out::println);*/

      //Print Headers
        System.out.println("Country                             Internet Users          Literacy");
        System.out.println("--------------------------------------------------------------------");

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

