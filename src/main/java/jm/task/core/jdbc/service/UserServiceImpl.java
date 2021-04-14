package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    public void createUsersTable() {
        UserDaoJDBCImpl dao = UserDaoJDBCImpl.getInstance();
        dao.createUsersTable();

    }

    public void dropUsersTable() {
        UserDaoJDBCImpl dao = UserDaoJDBCImpl.getInstance();
        dao.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {
        UserDaoJDBCImpl dao = UserDaoJDBCImpl.getInstance();
        dao.saveUser(name,lastName,age);

    }

    public void removeUserById(long id) {
        UserDaoJDBCImpl dao = UserDaoJDBCImpl.getInstance();
        dao.removeUserById(id);

    }

    public List<User> getAllUsers() {
        UserDaoJDBCImpl dao = UserDaoJDBCImpl.getInstance();
        dao.getAllUsers();

        return null;
    }

    public void cleanUsersTable() {
        UserDaoJDBCImpl dao = UserDaoJDBCImpl.getInstance();
        dao.cleanUsersTable();

    }

}
