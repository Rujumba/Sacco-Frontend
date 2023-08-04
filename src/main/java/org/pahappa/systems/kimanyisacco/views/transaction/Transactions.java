package org.pahappa.systems.kimanyisacco.views.transaction;

import org.pahappa.systems.kimanyisacco.controller.Hyperlinks;
import org.pahappa.systems.kimanyisacco.models.Account;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.models.UserTransaction;
import org.pahappa.systems.kimanyisacco.services.SaccoServiceImp;
import org.pahappa.systems.kimanyisacco.services.SaccoServices;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean (name = "transactionsBean")
@ViewScoped
public class Transactions {
    private UserTransaction userTransaction;

    private Account account;

    private final String base = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    private SaccoServices saccoServices;

    private int x;

    private User currentUser;

    private Date systemTime;

    FacesMessage message;

    public SaccoServices getSaccoServices() {
        return saccoServices;
    }

    public void setSaccoServices(SaccoServices saccoServices) {
        this.saccoServices = saccoServices;
    }

    public Date getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(Date systemTime) {
        this.systemTime = systemTime;
    }

    private List<UserTransaction> allWithDrawsOfUser;

    private List<UserTransaction> allUserTransactions;

    public Transactions(){
        this.userTransaction= new UserTransaction();
        this.saccoServices = new SaccoServiceImp();
        this.systemTime = new Date();
        this.account = new Account();
    }

    @PostConstruct
    public void init() {
        currentUser = getCurrentUser();

        // Prevent non authenticated users from accessing this page
        try {
            if (currentUser == null) {
                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                externalContext.getSessionMap().put("currentUser", null);
                externalContext.redirect(externalContext.getRequestContextPath() + Hyperlinks.LOGIN);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Account current = currentUser.getAccount();

        allWithDrawsOfUser = new ArrayList<>();

        allUserTransactions = saccoServices.UserTransactions(current);
        for (UserTransaction userTransaction : allUserTransactions) {
            if (userTransaction.getType().equals("WITHDRAWAL") && userTransaction.getStatus().equals("approved") && userTransaction.getAccount().getAccountNumber().equals(currentUser.getAccount().getAccountNumber()) ) {
                this.allWithDrawsOfUser.add(userTransaction);
            }
        }
        x= allUserTransactions.size();
    }

    public UserTransaction getUserTransaction() {
        return userTransaction;
    }

    public void setUserTransaction(UserTransaction userTransaction) {
        this.userTransaction = userTransaction;
    }

    public void saveMMTransaction() throws IOException {
        User user = getCurrentUser();
        userTransaction.setStatus("pending approval");
        userTransaction.setDateCreated(systemTime);
        userTransaction.setAccount(user.getAccount());

        userTransaction.setMethod("MobileMoney");

        saveTransaction(user);
    }

    public void saveBankTransaction() throws IOException {
        User user = getCurrentUser();
        userTransaction.setDateCreated(systemTime);
        userTransaction.setAccount(user.getAccount());

        userTransaction.setMethod("Bank");

        saveTransaction(user);

    }

    private void saveTransaction(User user) throws IOException {

        double currentAmount = user.getAccount().getAmount();
        System.out.println(currentAmount);
        if(userTransaction.getType().equals("DEPOSIT")){
            if(userTransaction.getAmount()!=0.0){
                userTransaction.setStatus("Deposited");
                saccoServices.saveTransaction(userTransaction);
                account.setAmount(currentAmount + userTransaction.getAmount());
                user.getAccount().setAmount(currentAmount + userTransaction.getAmount());
                System.out.println(this.account.getAmount());
                account.setAccountNumber(user.getAccount().getAccountNumber());

                saccoServices.updateAccount(this.account);

                this.account = new Account();
                userTransaction = new UserTransaction();
                currentAmount= 0.0;
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Deposit is successful!", "Deposit is successful!");
                FacesContext.getCurrentInstance().addMessage("message", message);
            }else {
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning : The amount field is null!", "");
                FacesContext.getCurrentInstance().addMessage("messages", message);
            }

        }else{
            boolean exists = false;
            for (UserTransaction x: allUserTransactions) {

                if(user.getAccount().getAccountNumber().equals(x.getAccount().getAccountNumber()) && x.getStatus().equals("pending approval") || x.getStatus().equals("approved") ){
                    exists = true;
                }else{
                    exists=false;
                }

            }
            if (userTransaction.getAmount() != 0.0 && userTransaction.getAmount() <= user.getAccount().getAmount() ) {
               if(!exists){
                   if (currentUser.getAccount().getAmount()>userTransaction.getAmount()){
                       userTransaction.setStatus("pending approval");
                       saccoServices.saveTransaction(userTransaction);

                       message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Withdraw is submitted successfully for approval!", "");
                       FacesContext.getCurrentInstance().addMessage("message", message);
                   }else{
                       message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Warning: Withdraw amount is greater than your account balance!", "");
                       FacesContext.getCurrentInstance().addMessage("message", message);
                   }
               }else{
                   message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Warning: You already have a pending or approved withdraw!", "");
                   FacesContext.getCurrentInstance().addMessage("message", message);
               }

            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning: The amount field is null or less than zero!", "");

                FacesContext.getCurrentInstance().addMessage("messages", message);
            }
        }


    }

    public void ConfirmedWithdraw(UserTransaction transaction) throws IOException {
        User user = getCurrentUser();
        double currentAmount= user.getAccount().getAmount();
        account.setAmount(currentAmount - transaction.getAmount());
        transaction.setStatus("Withdrawn");
        saccoServices.updateWithdrawalStatus(transaction);
        user.getAccount().setAmount(currentAmount - transaction.getAmount());
        System.out.println(this.account.getAmount());
        account.setAccountNumber(user.getAccount().getAccountNumber());

        saccoServices.updateAccount(this.account);

        this.account = new Account();
        userTransaction = new UserTransaction();
        currentAmount= 0.0;
        FacesContext.getCurrentInstance().getExternalContext().redirect(base+ Hyperlinks.HISTORY);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Withdraw successful! Thank you for saving with us", ""));
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public User getCurrentUser() {
        // Used to fetch currently logged in user
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        return (User) externalContext.getSessionMap().get("currentUser");
    }

    public List<UserTransaction> UserTransactions() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(base+ Hyperlinks.TRANSACTION);
        User user = getCurrentUser();
        Account current = user.getAccount();
        System.out.println(current);

        return allUserTransactions;
    }

    public List<UserTransaction> getAllUserTransactions() {
        return allUserTransactions;
    }

    public void setAllUserTransactions(List<UserTransaction> allUserTransactions) {
        this.allUserTransactions = allUserTransactions;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public List<UserTransaction> getAllWithDrawsOfUser() {
        return allWithDrawsOfUser;
    }

    public void setAllWithDrawsOfUser(List<UserTransaction> allWithDraws) {
        this.allWithDrawsOfUser = allWithDraws;
    }

    public void redirectHistory() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(base+ Hyperlinks.HISTORY);
    }
}
