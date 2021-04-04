package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args){
        UserServiceImpl uservice = new UserServiceImpl();
        uservice.createUsersTable();
        uservice.saveUser("Jack","Back", (byte) 44);
        uservice.saveUser("Steve","Jobs", (byte) 20);
        uservice.saveUser("Warren","Buffet", (byte) 14);
        uservice.saveUser("Stop","Me", (byte) 31);
        List<User> temp = uservice.getAllUsers();
        Iterator iterator = temp.iterator();
        while(iterator.hasNext()) {
            Object next = iterator.next();
            User us = (User)next;
            System.out.println(us.toString());
        }
        uservice.cleanUsersTable();
        uservice.dropUsersTable();
    }
}
