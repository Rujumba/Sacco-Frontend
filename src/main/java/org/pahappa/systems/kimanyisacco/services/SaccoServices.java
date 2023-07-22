package org.pahappa.systems.kimanyisacco.services;

import org.pahappa.systems.kimanyisacco.models.Loan;
import org.pahappa.systems.kimanyisacco.models.UserTransaction;
import org.pahappa.systems.kimanyisacco.models.User;

import java.util.List;

public interface SaccoServices  {

    void registerUser(User user);

    void createLoan();

    void createDeposit(UserTransaction transaction);

    void createWithdrawal();

    List<User> getAllUsers();

    List<User> getAllUsersOfStatus();

    void updateUser(User user);

    List<Loan> getAllLoans();

    void updateLoanStatus();

    List<UserTransaction> getAllDeposits();

    void updateDepositStatus();

    List<UserTransaction> getAllWithdrawals();

    void updateWithdrawalStatus();

    void addLoanRequest();

    void deleteUser(User user);

}
