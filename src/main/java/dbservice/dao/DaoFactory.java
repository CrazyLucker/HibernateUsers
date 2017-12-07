package dbservice.dao;

import dbservice.DBHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {
    public static UserDao getRealisation() {
        Properties properties = new Properties();
        InputStream inputStream = DBHelper.class.getClassLoader().getResourceAsStream("application.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String realisation = properties.getProperty("realisation");
        switch (realisation) {
            case "jdbc": {
                return new UserDaoJDBCImpl();
            }
            case "hibernate": {
                return new UserDaoHibernateImpl();
            }
            default: {
                return null;
            }
        }
    }

}
