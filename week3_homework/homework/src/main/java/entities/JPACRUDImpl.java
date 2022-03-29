package entities;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.*;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class JPACRUDImpl {

    private DataSource getDataSource() {
        final PGSimpleDataSource dataSource = new PGSimpleDataSource();
//        dataSource.setDatabaseName("OrmDemo");
        dataSource.setUser("yikewang");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/");
        return dataSource;
    }

    private Properties getProperties() {
        final Properties properties = new Properties();
//        properties.put("hibernate.default_schema", "my_default_schema");
        properties.put( "hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect" );
        properties.put( "hibernate.connection.driver_class", "org.postgresql.Driver" );
//        properties.put("hibernate.show_sql", "true");
        return properties;
    }

    private EntityManagerFactory entityManagerFactory(DataSource dataSource, Properties hibernateProperties ){
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan( "entities");
        em.setJpaVendorAdapter( new HibernateJpaVendorAdapter() );
        em.setJpaProperties( hibernateProperties );
        em.setPersistenceUnitName( "try-unit" );
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.afterPropertiesSet();
        return em.getObject();
    }

    public static void main(String[] args) {
        JPACRUDImpl jpaDemo = new JPACRUDImpl();
        DataSource dataSource = jpaDemo.getDataSource();
        Properties properties = jpaDemo.getProperties();
        EntityManagerFactory entityManagerFactory = jpaDemo.entityManagerFactory(dataSource, properties);
        EntityManager em = entityManagerFactory.createEntityManager();
        PersistenceUnitUtil unitUtil = entityManagerFactory.getPersistenceUnitUtil();

        deleteFunc(em);
//        retrieveData(em);

//        addToJunctionTable3(em);
//        withoutOrphanRemove(em);

//        List<Teacher> tList = em.createQuery("select t from Teacher t join t.teacher_students ts").getResultList();
//        Teacher t = tList.get(0);
//        System.out.println("**************************************");
//        System.out.println("class is loaded : " + unitUtil.isLoaded(t));
//        System.out.println("collection is loaded : " + unitUtil.isLoaded(t, "teacher_students"));
//        List<Teacher_Student> teacher_students = t.getTeacher_students();
//        System.out.println("collection is loaded : " + unitUtil.isLoaded(teacher_students, "teacher_students"));
//        System.out.println(teacher_students);
//        System.out.println("collection is loaded : " + unitUtil.isLoaded(teacher_students, "teacher_students"));
//        System.out.println("**************************************");
    }

    private static void createData(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Student s = new Student();
        s.setName("Naomi");
        Project p = new Project();
        em.persist(p);
        p.setProjectName("JPA");
        Student_Project sp = new Student_Project();
        sp.setStu(s);
        sp.setProject(p);
        p.addStudent_Projects(sp);

        em.persist(s);
        tx.commit();
    }

    private static void insertData(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query query = em.createNativeQuery("INSERT INTO STUDENT_PROJECT (s_id, p_id) VALUES (?, ?)");
        query.setParameter(1, 11);
        query.setParameter(2, 12);
        query.executeUpdate();
        tx.commit();
    }

    private static void retrieveData(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Student s = em.find(Student.class, 2);
        Project p = em.find(Project.class, 2);

        Student_Project sp = new Student_Project();
        sp.setStu(s);
        sp.setProject(p);
        em.persist(sp);
        tx.commit();
    }

    private static void updateFunc(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Student s = em.find(Student.class, 2);
        s.setName("NaomiWang");
        tx.commit();
    }

    private static void deleteFunc(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Student s = em.find(Student.class, 2);
        em.remove(s);
        tx.commit();
    }


//    private static void createFunc(EntityManager em) {
//        EntityTransaction et = em.getTransaction();
//        et.begin();
//        Employee e = new Employee();
//        e.setFirst_name("Chen");
//        e.setLast_name("JenHao");
//
//        Supervisor s = new Supervisor();
//        em.persist(s);
//        s.setName("Mike");
//
//        Supervisor_Employee se = new Supervisor_Employee();
//        se.setEmployee(e);
//        se.setSupervisor(s);
//        s.addSupervisor_Employee(se);
//
//        em.persist(s);
//        et.commit();
//    }





}

