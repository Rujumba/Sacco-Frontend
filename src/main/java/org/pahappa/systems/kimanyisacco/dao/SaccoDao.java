package org.pahappa.systems.kimanyisacco.dao;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pahappa.systems.kimanyisacco.config.SessionConfiguration;
import org.pahappa.systems.kimanyisacco.models.Account;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.models.UserTransaction;

import java.util.List;



public class SaccoDao {

    private List<User> user;

    public void save(User user) {
        Transaction transaction = null;
        try {
            Session session = SessionConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    public boolean createIfEmpty() {

        Session session = SessionConfiguration.getSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(User.class);
            criteria.setProjection(Projections.rowCount());
            Long count = (Long) criteria.uniqueResult();
            return count == 0L;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public List<User> getAllUsersOfStatus(String status) {
        Session session = SessionConfiguration.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("status", status));
        System.out.println("checked");
        return criteria.list();
    }

    public void updateUser(User user) {
        Transaction transaction = null;
        try {
            Session session = SessionConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            System.out.println("updated");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    public void removeUser(User user) {
        Transaction transaction = null;
        try {
            Session session = SessionConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(user);
            System.out.println("deleted");
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    public void updateAccount(Account account) {
        Transaction transaction = null;
        try {
            Session session = SessionConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(account);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    public List<UserTransaction> getUserTransactions(Account account) {
        Session session = SessionConfiguration.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(UserTransaction.class);
        criteria.add(Restrictions.eq("account", account));
        System.out.println("checked");
        return criteria.list();

    }

    //check if the user exists in the database
    public User checkUserExistence(String email) {
        Session session = SessionConfiguration.getSessionFactory().openSession();
        Transaction transaction = null;
        User user = null;

        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("email", email));
            criteria.setMaxResults(1); // To limit the result to a single user
            user = (User) criteria.uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return user;
    }

    public boolean checkEmailExists(String email) {
        boolean userExists = false;
        Session session = SessionConfiguration.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("email", email));
            criteria.setMaxResults(1); // To limit the result to a single user
            User user = (User) criteria.uniqueResult();

            if (user != null) {
                userExists = true;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userExists;
    }

    public List<User> getAllUsers() {
        Session session = SessionConfiguration.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(User.class);
        return criteria.list();
    }
}