package dbservice.dao;

import dbservice.DBHelper;
import dbservice.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public void addUser(User user){
        Session session = DBHelper.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public ArrayList<User> getAllUsers(){
        Session session = DBHelper.getInstance().getSessionFactory().openSession();
        List users = session.createQuery("FROM User").list();
        return (ArrayList<User>)users;
    }


    public void deleteUser(int id) {
        Session session = DBHelper.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
    }

    public User getUserById(int id) {
        Session session = DBHelper.getInstance().getSessionFactory().openSession();
        User user = (User) session.get(User.class, id);
        session.close();
        return user;
    }

    public void updateUser(User user){
        Session session = DBHelper.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }
}
