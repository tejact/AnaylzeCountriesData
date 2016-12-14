import com.tejatummalapalli.analyzeCountires.dao.CountryDao;
import com.tejatummalapalli.analyzeCountires.dao.SimpleCountryDaoImpl;
import com.tejatummalapalli.analyzeCountries.model.Country;

import java.util.List;
import java.util.Scanner;

public class Main {

    static CountryDao countryDao = new SimpleCountryDaoImpl();

    public static void main(String args[]) {
        System.out.println("Hello..Welcome to Hibernate");


        //addCountry();
        //deleteCountry();


        //displayFormattedData();

        System.out.println(countryDao.getMinMaxStat("Minimum", "Literacy"));
        System.out.println(countryDao.getMinMaxStat("Maximum", "Literacy"));
        System.out.println(countryDao.getMinMaxStat("Minimum", "InternetUsers"));
        System.out.println(countryDao.getMinMaxStat("Maximum", "InternetUsers"));

        System.out.println("The co-relation coefficient is " + countryDao.getCorrelationCoefficient());


        //Prompting the User//
        Scanner scanner = new Scanner(System.in);
        Prompter prompter = new Prompter(scanner);

        String prompt;
        String userOption;
        do {
            prompt = "1.View All Countries 2.Add Country 3.Delete Country 4.Update Country" +
                    "\n5. View Corelation Coefficient 5. Max Adult Literacy 7.Min Adult Literacy" +
                    "\n8. Max Internet Users 9.Min Internet Users 10.Exit";
            userOption = prompter.getDataFromUser(prompt);

            if (userOption.equals("1")) {
                displayFormattedData();
            } else if (userOption.equals("5")) {
                System.out.println("The co-relation coefficient is " + countryDao.getCorrelationCoefficient());
            } else if(userOption.equals("10")) {
                System.out.println("Thanks you!!!");
            }
        }while(!userOption.equals("10"));
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
        try {
            countryDao.addCountry(dummyCountry);
        } catch( org.hibernate.exception.ConstraintViolationException e1) {
            System.out.println("********Following Constraint violated*****");
            e1.printStackTrace();
        } catch ( Exception e) {
            System.out.println("Parent Exception is catched");
            e.printStackTrace();
        }
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

