package org.pahappa.systems.kimanyisacco.services;


import org.pahappa.systems.kimanyisacco.dao.LoanDao;
import org.pahappa.systems.kimanyisacco.dao.SaccoDao;
import org.pahappa.systems.kimanyisacco.dao.TransactionDao;
import org.pahappa.systems.kimanyisacco.models.Account;
import org.pahappa.systems.kimanyisacco.models.Loan;
import org.pahappa.systems.kimanyisacco.models.UserTransaction;
import org.pahappa.systems.kimanyisacco.models.User;

import java.util.List;
import java.util.Random;

public class SaccoServiceImp implements SaccoServices{

    private final SaccoDao saccoDao = new SaccoDao();

    private final LoanDao loanDao = new LoanDao();

    private final TransactionDao transactionDao = new TransactionDao();

    @Override
    public void registerUser(User user) {
        Account acc= new Account();
        Random random = new Random();
        acc.setAccountNumber("KMS"+new Random().nextInt(100000000));
        acc.setAmount(0.0F);
        user.setAccount(acc);
        user.setStatus("pending");
        saccoDao.save(user);
    }

    public void createLoan(Loan loan) {
        loanDao.saveLoan(loan);
    }

    public void createDeposit(UserTransaction transaction) {
        transactionDao.saveTransaction(transaction);
    }

    public void createWithdrawal() {
    }

    public boolean numberOfUsers() {

        return saccoDao.createIfEmpty();
    }

    public List<User> getAllUsersOfStatus() {

        return saccoDao.getAllUsersOfStatus();
    }

    public List<User> getAllUsers() {

        return saccoDao.getAllUsers();
    }

    public void updateUser(User user) {
        saccoDao.updateUser(user);
    }

    public List<Loan> getAllLoans() {
        return loanDao.getAllLoans();
    }

    public void updateLoanStatus(Loan loan) {
        loanDao.updateLoan(loan);
    }

    public List<UserTransaction> allTransactions() {
        return transactionDao.allTransactions();
    }

    public void updateDepositStatus() {
    }

    public List<UserTransaction> AllWithdrawals() {
        return transactionDao.allWithdrawTransactions();
    }

    public void updateWithdrawalStatus(UserTransaction userTransaction) {
        transactionDao.updateWithdraws(userTransaction);
    }

    public List<Loan> pendingLoanRequests() {
        return loanDao.pendingLoanRequests();
    }

    public void deleteUser(User user) {
        saccoDao.removeUser(user);
    }

    public void saveTransaction(UserTransaction transaction){
        transactionDao.saveTransaction(transaction);
    }

    public void updateAccount(Account account){
        saccoDao.updateAccount(account);
    }

    public List<UserTransaction> UserTransactions(Account account){

       return saccoDao.getUserTransactions(account);
    }

    @Override
    public User do_Login(String email) {

        return saccoDao.checkUserExistence(email);
    }

    @Override
    public boolean emailExists(String email) {
        return saccoDao.checkEmailExists(email);
    }

    public List<Loan> getLoanOfUser(Account account){
        return loanDao.getLoanOfUser(account);
    }


}
