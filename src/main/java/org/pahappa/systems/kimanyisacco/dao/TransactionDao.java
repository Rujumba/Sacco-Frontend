package org.pahappa.systems.kimanyisacco.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.pahappa.systems.kimanyisacco.config.SessionConfiguration;
import org.pahappa.systems.kimanyisacco.models.UserTransaction;
import java.util.List;



public class TransactionDao {

    private List<UserTransaction> userTransactions;
    private String email;

    public void saveTransaction(UserTransaction userTransaction){
        Transaction transaction = null;
        try{
            Session session = SessionConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(userTransaction);
            transaction.commit();
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

//    public List<User> getAllUserList(){
//
//        Session session = SessionConfiguration.getSessionFactory().openSession();
//        return session.createCriteria(User.class).list();
//    }

    public List<UserTransaction> getAllUserTransactions(){
//        Session session = SessionConfiguration.getSessionFactory().openSession();
//        return session.createCriteria(User.class).add(org.hibernate.criterion.Restrictions.eq("status", status)).list();

        Session session = SessionConfiguration.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(UserTransaction.class);
        criteria.add(Restrictions.eq("email", email));
        System.out.println("checked");
        return criteria.list();
    }


    public void setEmail(String email) {
        this.email = email;
    }
}
