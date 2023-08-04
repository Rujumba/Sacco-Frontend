package org.pahappa.systems.kimanyisacco.dao;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.pahappa.systems.kimanyisacco.config.SessionConfiguration;
import org.pahappa.systems.kimanyisacco.models.Account;
import org.pahappa.systems.kimanyisacco.models.Loan;

import java.util.List;

public class LoanDao {
    public void saveLoan(Loan loan) {
        Transaction transaction = null;
        try {
            Session session = SessionConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(loan);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    public List<Loan> getAllLoans() {
        Session session = SessionConfiguration.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Loan.class);
        System.out.println("checked all loans");
        return criteria.list();

    }

    public List<Loan> pendingLoanRequests() {
        Session session = SessionConfiguration.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Loan.class);
        criteria.add(Restrictions.eq("status", "pending"));
        System.out.println("checked pending loans");
        return criteria.list();

    }

    public void updateLoan(Loan loan){
        Transaction transaction = null;
        try {
            Session session = SessionConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(loan);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    public List<Loan> getLoanOfUser(Account account){
        Session session = SessionConfiguration.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Loan.class);
        criteria.add(Restrictions.eq("account", account));
        System.out.println("loans of user");
        return criteria.list();
    }

}
