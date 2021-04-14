package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String dbDriver = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/db?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String name = "max";
    private static final String password = "max";
    private static Util instance;
    private Util(){}

    public static Util getInstance(){
        if(instance == null){
            instance = new Util();
        }
        return instance;
    }

    public Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName(dbDriver);
            connection = DriverManager.getConnection(URL,name,password);
            System.out.println("Connection ok");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
        return connection;
    }
}
