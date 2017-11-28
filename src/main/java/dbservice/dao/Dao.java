package dbservice.dao;

import dbservice.model.User;

import java.util.ArrayList;

public interface Dao {
    public void addUser(User user);

    public ArrayList<User> getAllUsers();

    public void deleteUser(int id);

    public User getUserById(int id);

    public void updateUser(int id, String name, String surname, int age);

    public void updateUser(User user);
}
