package dbservice.dao;

import dbservice.model.User;

import java.util.ArrayList;

public interface UserDao {
    void addUser(User user);

    ArrayList<User> getAllUsers();

    void deleteUser(int id);

    User getUserById(int id);

    void updateUser(User user);
}
