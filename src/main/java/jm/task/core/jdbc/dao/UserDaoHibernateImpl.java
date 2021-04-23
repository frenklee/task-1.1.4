package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    SessionFactory factory = Util.getInstance().getSessionFactory();
    private static UserDaoHibernateImpl instance;
    private UserDaoHibernateImpl() {

    }
    public static UserDaoHibernateImpl getInstance(){
        if(instance == null){
            instance = new UserDaoHibernateImpl();
        }
        return instance;
    }


    @Override
    public void createUsersTable() {
        Session session = factory.getCurrentSession();
        try{
            session.beginTransaction();
            session.createQuery("CREATE TABLE IF NOT EXISTS tabl (\n" +
                    "  id INT NOT NULL AUTO_INCREMENT,\n" +
                    "  name VARCHAR(45) NULL,\n" +
                    "  lastName VARCHAR(45) NULL,\n" +
                    "  age INT NULL,\n" +
                    "  PRIMARY KEY (id))").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            factory.close();
        }

    }

    @Override
    public void dropUsersTable() {
        Session session = factory.getCurrentSession();
        try{
            session.beginTransaction();
            session.createQuery("DROP TABLE IF EXISTS tabl").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = factory.getCurrentSession();
        try{
            User user = new User(name,lastName,age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = factory.getCurrentSession();
        try{
            session.beginTransaction();
            Object temp = session.load(User.class,id);
            session.delete(temp);
            session.getTransaction().commit();
        } catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = factory.getCurrentSession();
        List<User> users = null;
        try{
            session.beginTransaction();
            users = session.createQuery("from tabl").list();
            for(User tempUser : users){
                System.out.println(tempUser);
            }
            session.getTransaction().commit();
        } catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            factory.close();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = factory.getCurrentSession();
        try{
            session.beginTransaction();
            session.createQuery("TRUNCATE TABLE tabl").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
