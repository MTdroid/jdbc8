package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao daoJDBC = new UserDaoJDBCImpl();
    @Override
    public void createUsersTable() {
        daoJDBC.createUsersTable();

    }
    @Override
    public void dropUsersTable() {
        daoJDBC.dropUsersTable();

    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        daoJDBC.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        daoJDBC.removeUserById(id);

    }
    @Override
    public List<User> getAllUsers() throws SQLException {

        return daoJDBC.getAllUsers();

    }
    @Override
    public void cleanUsersTable() throws SQLException {
        daoJDBC.cleanUsersTable();

    }
}
