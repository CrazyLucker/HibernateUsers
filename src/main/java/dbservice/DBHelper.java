package dbservice;

import dbservice.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBHelper {
    private static DBHelper instance;
    private SessionFactory sessionFactory = buildSessionFactory();
    private Connection connection = buildConnection();

    private DBHelper() {
    }

    public static synchronized DBHelper getInstance(){
        if (instance == null){
            instance = new DBHelper();
        }
        return instance;
    }

    private SessionFactory buildSessionFactory() {
        try {
            Properties properties = new Properties();
            InputStream inputStream = DBHelper.class.getClassLoader().getResourceAsStream("application.properties");
            properties.load(inputStream);
            String driver = properties.getProperty("hibernate.connection.driver_class");
            if (driver != null){
                Configuration configuration = new Configuration();
                configuration.addAnnotatedClass(User.class);
                configuration.setProperty("hibernate.connection.driver_class", properties.getProperty("hibernate.connection.driver_class"));
                configuration.setProperty("hibernate.connection.url", properties.getProperty("hibernate.connection.url"));
                configuration.setProperty("hibernate.connection.username", properties.getProperty("hibernate.connection.username"));
                configuration.setProperty("hibernate.connection.password", properties.getProperty("hibernate.connection.password"));
                configuration.setProperty("hibernate.dialect", properties.getProperty("hibernate.dialect"));
                configuration.setProperty("hibernate.hbm2ddl.auto", properties.getProperty("hibernate.hbm2ddl.auto"));
                configuration.setProperty("hibernate.show_sql", properties.getProperty("hibernate.show_sql"));

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
                builder.applySettings(configuration.getProperties());
                ServiceRegistry serviceRegistry = builder.build();
                return configuration.buildSessionFactory(serviceRegistry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private Connection buildConnection() {
        Properties properties = new Properties();
        try {
            InputStream inputStream = DBHelper.class.getClassLoader().getResourceAsStream("application.properties");
            properties.load(inputStream);
            String driver = properties.getProperty("jdbc.driver");
            if (driver != null){
                String url = properties.getProperty("jdbc.url");
                String username = properties.getProperty("jdbc.username");
                String pass = properties.getProperty("jdbc.password");
                return DriverManager.getConnection(url, username, pass);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection getConnection() {
        return connection;
    }
}
