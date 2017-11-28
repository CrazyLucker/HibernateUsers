package dbservice.dao;

import dbservice.DBService;
import dbservice.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class DaoImpl implements Dao{
    public void addUser(User user){
        Session session = DBService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public ArrayList<User> getAllUsers(){
        Session session = DBService.getSessionFactory().openSession();
        List users = session.createQuery("FROM User").list();
        return (ArrayList<User>)users;
    }


    public void deleteUser(int id) {
        Session session = DBService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
    }

    public User getUserById(int id) {
        Session session = DBService.getSessionFactory().openSession();
        User user = (User) session.get(User.class, id);
        session.close();
        return user;
    }

    public void updateUser(int id, String name, String surname, int age) {
        Session session = DBService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User)session.get(User.class, id);
        user.setName(name);
        user.setSurname(surname);
        user.setAge(age);
        session.update(user);
        transaction.commit();
        session.close();
    }
}
