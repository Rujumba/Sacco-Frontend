package org.pahappa.systems.kimanyisacco.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.pahappa.systems.kimanyisacco.config.SessionConfiguration;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.models.UserTransaction;

import javax.persistence.Query;
import java.util.List;


public class TransactionDao {

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



    public List<UserTransaction> allWithdrawTransactions(){

        Session session = SessionConfiguration.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(UserTransaction.class);
        criteria.add(Restrictions.eq("type", "WITHDRAWAL"));
        criteria.add(Restrictions.eq("status", "pending approval"));
        System.out.println("checked");
        return criteria.list();
    }

    public List<UserTransaction> allTransactions(){

        Session session = SessionConfiguration.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(UserTransaction.class);
        return criteria.list();
    }

    public void updateWithdraws(UserTransaction userTransaction){
        Transaction transaction = null;
        try{
            Session session = SessionConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(userTransaction);
            transaction.commit();
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

//    public long countAllTransactions() {
//        try (Session session = SessionConfiguration.getSessionFactory().openSession()) {
//            Criteria criteria = session.createCriteria(UserTransaction.class);
//            criteria.setProjection(Projections.rowCount());
//            Long count = (Long) criteria.uniqueResult();
//            return count != null ? count : 0;
//        }
//    }

//    public void Account getAccountById(long accountId) {
//        try {
//            Session session = SessionConfiguration.getSessionFactory().openSession();
//            Query query = session.createQuery("FROM Account WHERE account_id = :accountId");
//            query.setParameter("accountId", accountId);
//            return (Account) query.uniqueResult();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }


}
