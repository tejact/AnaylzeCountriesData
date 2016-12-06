import com.tejatummalapalli.analyzeCountries.model.Country;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class Main {
    private final SessionFactory sessionFactory = buildSessionFactory();

    private SessionFactory buildSessionFactory() {
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static void main(String args[]) {
        System.out.println("Hello..Welcome to Hibernate");


        Country dummyCountry = new Country.CountryBuilder()
                                    .withName("India2")
                                    .withAdultLiteracyRate(90l)
                                    .withAdultLiteracyRate(80l)
                                    .build();

        Main main = new Main();

        List allCountries = main.getAllCountries();

                allCountries
                .stream().
                forEach(System.out::println);


    }

    public List<Country> getAllCountries() {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Country.class);

        List<Country> countries = criteria.list();


        session.close();

        return countries;
    }
}

