package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    public void createUsersTable() {
        UserDaoHibernateImpl dao = UserDaoHibernateImpl.getInstance();
        dao.createUsersTable();

    }

    public void dropUsersTable() {
        UserDaoHibernateImpl dao = UserDaoHibernateImpl.getInstance();
        dao.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {
        UserDaoHibernateImpl dao = UserDaoHibernateImpl.getInstance();
        dao.saveUser(name,lastName,age);

    }

    public void removeUserById(long id) {
        UserDaoHibernateImpl dao = UserDaoHibernateImpl.getInstance();
        dao.removeUserById(id);

    }

    public List<User> getAllUsers() {
        UserDaoHibernateImpl dao = UserDaoHibernateImpl.getInstance();
        List<User> result = dao.getAllUsers();

        return result;
    }

    public void cleanUsersTable() {
        UserDaoHibernateImpl dao = UserDaoHibernateImpl.getInstance();
        dao.cleanUsersTable();

    }

}
