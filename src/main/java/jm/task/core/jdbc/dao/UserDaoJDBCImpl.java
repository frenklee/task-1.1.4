package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static UserDaoJDBCImpl instance;
    private UserDaoJDBCImpl() {

    }
    public static UserDaoJDBCImpl getInstance(){
        if(instance == null){
            instance = new UserDaoJDBCImpl();
        }
        return instance;
    }

    public void createUsersTable() {
        Connection connection = Util.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "CREATE TABLE `db`.`tabl` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NULL,\n" +
                "  `lastName` VARCHAR(45) NULL,\n" +
                "  `age` INT NULL,\n" +
                "  PRIMARY KEY (`id`));";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {

                try {
                    if(preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if(connection != null) {
                        connection.close();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

        }
    }

    public void dropUsersTable() {
        Connection connection = Util.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "IF OBJECT_ID('db.tabl', 'U') IS NOT NULL " +
                "DROP TABLE db.tabl;";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {

                try {
                    if(preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if(connection != null) {
                        connection.close();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = Util.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO tabl (name,lastName,age) VALUES (?,?,?)";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setLong(3,age);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {

                try {
                    if(preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if(connection != null) {
                        connection.close();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

        }
    }

    public void removeUserById(long id) {
        Connection connection = Util.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM tabl where ID=?";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {

                try {
                    if(preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if(connection != null) {
                        connection.close();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

        }
    }

    public List<User> getAllUsers() {
        Connection connection = Util.getInstance().getConnection();
        List<User> userList = new ArrayList<>();
        Statement statement = null;
        String sql = "SELECT * FROM db.tabl";
        ResultSet resultSet = null;
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                User user = new User();
                user.setId((long) resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
            Iterator<User> iter = userList.iterator();
            while(iter.hasNext()){
                iter.next().toString();
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {

                try {
                    if(resultSet != null) {
                        resultSet.close();
                    }
                    if(statement != null) {
                        statement.close();
                    }
                    if(connection!=null) {
                        connection.close();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

        }
        return userList;
    }

    public void cleanUsersTable() {
        Connection connection = Util.getInstance().getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "TRUNCATE FROM tabl";
        try{
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
                try {
                    if(resultSet != null) {
                        resultSet.close();
                    }
                    if(statement != null) {
                        statement.close();
                    }
                    if(connection != null) {
                        connection.close();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

        }
    }
}
