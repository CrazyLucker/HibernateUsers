package dbservice.dao;

import dbservice.DBHelper;
import dbservice.model.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDaoJDBCImpl implements UserDao {

    @Override
    public void addUser(User user) {
        Connection connection = DBHelper.getInstance().getConnection();
        try(PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, surname, age) VALUES (?, ?, ?)")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setInt(3, user.getAge());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<User> getAllUsers() {
        Connection connection = DBHelper.getInstance().getConnection();
        ArrayList<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4)));
            }
            resultSet.close();
            statement.close();
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void deleteUser(int id) {
        Connection connection = DBHelper.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = (?)")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserById(int id) {
        Connection connection = DBHelper.getInstance().getConnection();
        try (PreparedStatement statement = createPreparedStatementForGetUserById(connection, id);
             ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            User user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private PreparedStatement createPreparedStatementForGetUserById(Connection con, int id) throws SQLException {
        PreparedStatement statement = con.prepareStatement("SELECT * FROM users WHERE id = (?)");
        statement.setInt(1, id);
        return statement;
    }

    @Override
    public void updateUser(User user) {
        Connection connection = DBHelper.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement("UPDATE users SET name = (?), surname = (?), age = (?) WHERE id = (?)")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setInt(3, user.getAge());
            statement.setInt(4, (int) user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
