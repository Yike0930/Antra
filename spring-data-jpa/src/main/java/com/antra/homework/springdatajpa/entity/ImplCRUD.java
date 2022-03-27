package com.antra.homework.springdatajpa.entity;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.postgresql.ds.PGSimpleDataSource;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class ImplCRUD {

    private DataSource getDataSource() {
        final PGSimpleDataSource dataSource = new PGSimpleDataSource();
//        dataSource.setDatabaseName("OrmDemo");
        dataSource.setUser("postgres");
        dataSource.setPassword("password");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/university");
        return dataSource;
    }

    private Properties getProperties() {
        final Properties properties = new Properties();
        properties.put( "hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect" );
        properties.put( "hibernate.connection.driver_class", "org.postgresql.Driver" );
//        properties.put("hibernate.show_sql", "true");
        return properties;
    }

    private EntityManagerFactory entityManagerFactory(DataSource dataSource, Properties hibernateProperties ){
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan( "com/antra/homework/springdatajpa/entity");
        em.setJpaVendorAdapter( new HibernateJpaVendorAdapter() );
        em.setJpaProperties( hibernateProperties );
        em.setPersistenceUnitName( "try-unit" );
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.afterPropertiesSet();
        return em.getObject();
    }

    //I'm not familiar with spring and JPA stuffs and this is the first time I write them. I'm still figuring them out.
    //I did some research but apparently I still have lots of questions.
    //Following codes are all I can write for now. Although I think they are all wrong...

    public void addOrder(EntityManager em, Order order){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(order);

        List<OrderProduct> op = order.getOrder_products();

        for(OrderProduct order_Product : op) {
            em.persist(order_Product);
            em.persist(order_Product.getProduct());
        }

        tx.commit();

    }



    public void updateOrder(EntityManager em){
        EntityTransaction tx = em.getTransaction();
        tx.begin();

    }

    public void deleteOrder(EntityManager em){}

    public List<OrderProduct> getOrder(EntityManager em){
        List<OrderProduct> res = new ArrayList<>();
        return res;
    }

}
