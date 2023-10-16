package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;

public class Main {
    private final static UserService userService = new UserServiceImpl();
    public static void main(String[] args) throws SQLException {
        userService.createUsersTable();
        userService.saveUser("a","a", (byte) 1);
        userService.saveUser("b","b", (byte) 2);
        userService.saveUser("c","c", (byte) 2);
        userService.saveUser("d","d", (byte) 32);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
