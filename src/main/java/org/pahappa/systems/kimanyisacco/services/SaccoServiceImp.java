package org.pahappa.systems.kimanyisacco.services;


import org.pahappa.systems.kimanyisacco.dao.SaccoDao;
import org.pahappa.systems.kimanyisacco.dao.TransactionDao;
import org.pahappa.systems.kimanyisacco.models.Loan;
import org.pahappa.systems.kimanyisacco.models.UserTransaction;
import org.pahappa.systems.kimanyisacco.models.User;

import javax.transaction.Transaction;
import java.util.List;
import java.util.Random;

public class SaccoServiceImp implements SaccoServices{

    private final SaccoDao saccoDao = new SaccoDao();

    private final TransactionDao transactionDao = new TransactionDao();

    @Override
    public void registerUser(User user) {
        Random random = new Random();
        user.setAccountNumber("ACC" + random.nextInt(1000));
        user.setStatus("pending");
        saccoDao.save(user);
    }

    public void createLoan() {
    }

    public void createDeposit(UserTransaction transaction) {
        transactionDao.saveTransaction(transaction);
    }

    public void createWithdrawal() {
    }

    public List<User> getAllUsers() {

        return saccoDao.getAllUserList();
    }

    public List<User> getAllUsersOfStatus() {

        return saccoDao.getAllUsersOfStatus();
    }

    public void updateUser(User user) {
        saccoDao.updateUser(user);
    }

    public List<Loan> getAllLoans() {
        return null;
    }

    public void updateLoanStatus() {
    }

    public List<UserTransaction> getAllDeposits() {
        return null;
    }

    public void updateDepositStatus() {
    }

    public List<UserTransaction> getAllWithdrawals() {
        return null;
    }

    public void updateWithdrawalStatus() {
    }

    public void addLoanRequest() {
    }

    public void deleteUser(User user) {
        saccoDao.removeUser(user);
    }


}
