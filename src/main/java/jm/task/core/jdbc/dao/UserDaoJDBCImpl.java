package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {



    private final Connection connection = new Util().getConnection();
    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `database`.users " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(30), lastName VARCHAR(30), age TINYINT)");
        } catch (Exception e) {
            System.out.println("При тестировании создания таблицы пользователей произошло исключение\n" + e.getMessage());
        }

    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS `database`.users");
        } catch (Exception e) {
            System.out.println("При тестировании удаления таблицы произошло исключение\n" + e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("INSERT INTO `database`.users (name, lastName, age) VALUES (?,?,?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println(" User с именем "+name+" добавлен в базу данных ");
        } catch (Exception e) {
            System.out.println("Во время тестирования сохранения пользователя произошло исключение\n" + e);


        }

    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `database`.users WHERE id =?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("При тестировании удаления пользователя по id произошло исключение\n" + e);
        }


    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery("SELECT * FROM `database`.users")) {
            while (result.next()) {
                User user = new User();
                user.setId(result.getLong("id"));
                user.setName(result.getString("name"));
                user.setLastName(result.getString("lastName"));
                user.setAge(result.getByte("age"));

                usersList.add(user);
            }

        } catch (Exception e) {
            System.out.println("При попытке достать всех пользователей из базы данных произошло исключение\n" + e);
        }
        return usersList;
    }


    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE `database`.users");

        } catch (Exception e) {
            System.out.println("При тестировании очистки таблицы пользователей произошло исключение\n" + e);

        }

    }
}