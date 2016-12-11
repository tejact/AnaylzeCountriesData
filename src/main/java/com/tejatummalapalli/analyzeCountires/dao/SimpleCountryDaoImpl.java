package com.tejatummalapalli.analyzeCountires.dao;

import com.tejatummalapalli.analyzeCountries.model.Country;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class SimpleCountryDaoImpl implements CountryDao {
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
}
