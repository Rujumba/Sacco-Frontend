package org.pahappa.systems.kimanyisacco.dao;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.pahappa.systems.kimanyisacco.config.SessionConfiguration;
import org.pahappa.systems.kimanyisacco.models.User;

import java.util.List;



public class SaccoDao {

    private List<User> user;
    public void save(User user){
        Transaction transaction = null;
        try{
            Session session = SessionConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    public List<User> getAllUserList(){

            Session session = SessionConfiguration.getSessionFactory().openSession();
            return session.createCriteria(User.class).list();
    }

    public List<User> getAllUsersOfStatus(){
//        Session session = SessionConfiguration.getSessionFactory().openSession();
//        return session.createCriteria(User.class).add(org.hibernate.criterion.Restrictions.eq("status", status)).list();

        Session session = SessionConfiguration.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("status", "pending"));
        System.out.println("checked");
        return criteria.list();
    }

    public void updateUser(User user){
        Transaction transaction = null;
        try{
            Session session = SessionConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    public void removeUser(User user){
        Transaction transaction = null;
        try{
            Session session = SessionConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(user);
            System.out.println("deleted");
            transaction.commit();
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }
}
