package org.pahappa.systems.kimanyisacco.services;

import org.pahappa.systems.kimanyisacco.models.Account;
import org.pahappa.systems.kimanyisacco.models.Loan;
import org.pahappa.systems.kimanyisacco.models.UserTransaction;
import org.pahappa.systems.kimanyisacco.models.User;

import java.util.List;

public interface SaccoServices  {

    void registerUser(User user);

    void createLoan(Loan loan);

    void createDeposit(UserTransaction transaction);

    void createWithdrawal();

    boolean numberOfUsers();

    List<User> getAllUsersOfStatus(String status);

    void updateUser(User user);

    List<Loan> getAllLoans();

    void updateLoanStatus(Loan loan);

    List<UserTransaction> allTransactions();

    void updateDepositStatus();

    List<UserTransaction> AllWithdrawals();

    void updateWithdrawalStatus(UserTransaction userTransaction);

    List<Loan> pendingLoanRequests();

    void deleteUser(User user);

    void saveTransaction(UserTransaction transaction);

    void updateAccount(Account account);

    List<UserTransaction> UserTransactions(Account account);

   User do_Login(String email);


   boolean emailExists(String email);

   List<User> getAllUsers();

   List<Loan> getLoanOfUser(Account account);


}
