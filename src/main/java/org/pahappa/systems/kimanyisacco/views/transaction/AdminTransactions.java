package org.pahappa.systems.kimanyisacco.views.transaction;
import org.pahappa.systems.kimanyisacco.controller.Hyperlinks;
import org.pahappa.systems.kimanyisacco.models.UserTransaction;
import org.pahappa.systems.kimanyisacco.services.SaccoServiceImp;
import org.pahappa.systems.kimanyisacco.services.SaccoServices;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

@ManagedBean (name = "adminTransactionsBean")
@ViewScoped
public class AdminTransactions {
    private UserTransaction userTransaction;

    private SaccoServices saccoServices;

    private final String base = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    public SaccoServices getSaccoServices() {
        return saccoServices;
    }

    public void setSaccoServices(SaccoServices saccoServices) {
        this.saccoServices = saccoServices;
    }

    private List<UserTransaction> allUserTransactions;

    private int numberOfTransactions;

    private List<UserTransaction> allUserWithdrawals;

    public AdminTransactions(){
        this.userTransaction= new UserTransaction();
        this.saccoServices = new SaccoServiceImp();
    }

    @PostConstruct
    public void init() {
        allUserTransactions = saccoServices.allTransactions();

        allUserWithdrawals = saccoServices.AllWithdrawals();

        numberOfTransactions= allUserWithdrawals.size();

    }

    public UserTransaction getUserTransaction() {
        return userTransaction;
    }

    public void setUserTransaction(UserTransaction userTransaction) {
        this.userTransaction = userTransaction;
    }

    public List<UserTransaction> UserTransactions() {
       return saccoServices.AllWithdrawals();
    }

    public List<UserTransaction> getAllUserTransactions() {
        return allUserTransactions;
    }

    public void setAllUserTransactions(List<UserTransaction> allUserTransactions) {
        this.allUserTransactions = allUserTransactions;
    }

    public void verifyWithdraw(UserTransaction userTransaction) throws IOException {
        userTransaction.setStatus("approved");
        saccoServices.updateWithdrawalStatus(userTransaction);
        FacesContext.getCurrentInstance().getExternalContext().redirect(base+ Hyperlinks.ADMINTRANSACTION);
    }

    public List<UserTransaction> getAllUserWithdrawals() {
        return allUserWithdrawals;
    }

    public void setAllUserWithdrawals(List<UserTransaction> allUserWithdrawals) {
        this.allUserWithdrawals = allUserWithdrawals;
    }


    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }

    public void setNumberOfTransactions(int numberOfTransactions) {
        this.numberOfTransactions = numberOfTransactions;
    }



}
