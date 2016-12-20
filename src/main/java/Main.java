import com.tejatummalapalli.analyzeCountires.dao.CountryDao;
import com.tejatummalapalli.analyzeCountires.dao.SimpleCountryDaoImpl;
import com.tejatummalapalli.analyzeCountries.model.Country;

import java.util.List;
import java.util.Scanner;

public class Main {

    static CountryDao countryDao = new SimpleCountryDaoImpl();

    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);
        Prompter prompter = new Prompter(scanner);

        String prompt;
        String userOption;
        do {
            prompt = "1.View All Countries 2.Add Country 3.Delete Country 4.Update Country" +
                    "\n5. View Corelation Coefficient 6. Max Adult Literacy 7.Min Adult Literacy" +
                    "\n8. Max Internet Users 9.Min Internet Users 10.Exit";
            userOption = prompter.getDataFromUser(prompt);

            if (userOption.equals("1")) {
                displayFormattedData();
            } else if(userOption.equals("2"))
            {
                String nameOfCountry = prompter.getDataFromUser("Please provide the name of the country");
                String countryCode = prompter.getDataFromUser("Please provide the unique code for the country");
                double adultLiteractyRate  = Double.parseDouble(prompter.getDataFromUser("Please provide the adult literacy rate for this country"));
                double internetUsers = Double.parseDouble(prompter.getDataFromUser("Please provide the percentage of internet users."));

                Country newCountry = new Country.CountryBuilder()
                        .withName(nameOfCountry)
                        .withCode(countryCode)
                        .withInternetUsers(internetUsers)
                        .withAdultLiteracyRate(adultLiteractyRate)
                        .build();

                addCountry(newCountry);

            }else if(userOption.equals("3"))
            {
                String countryName = prompter.getDataFromUser("Please enter the country name to be deleted");
                deleteCountry(countryName);
            }else if(userOption.equals("4"))
            {
                String countryID = prompter.getDataFromUser("Please enter the ID of the country you want ot update");

                String toBeUpdatedColum = prompter.getDataFromUser("What do you want to change? 1.Literacy Rate 2.Internet Users");

                String newValue = prompter.getDataFromUser("Please enter the new value");

                countryDao.updateCountry(countryID,toBeUpdatedColum,newValue);

            }else if(userOption.equals("5"))
            {
                System.out.println("The co-relation coefficient is " + countryDao.getCorrelationCoefficient());
            }else if(userOption.equals("6"))
            {
                System.out.println(countryDao.getMinMaxStat("Maximum", "Literacy"));
            }else if(userOption.equals("7"))
            {
                System.out.println(countryDao.getMinMaxStat("Minimum", "Literacy"));
            }else if(userOption.equals("8"))
            {
                System.out.println(countryDao.getMinMaxStat("Maximum", "InternetUsers"));
            }
            else if (userOption.equals("9")) {
                System.out.println(countryDao.getMinMaxStat("Minimum", "InternetUsers"));
            } else if(userOption.equals("10")) {
                System.out.println("Thanks you!!!");
            }
        }while(!userOption.equals("10"));
    }
    private static void deleteCountry(String countryName) {

        countryDao.deleteCountry(countryName);

    }

    private static void addCountry(Country newCountry) {
        try {
            countryDao.addCountry(newCountry);
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

