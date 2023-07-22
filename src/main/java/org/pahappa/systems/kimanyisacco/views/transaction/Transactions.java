package org.pahappa.systems.kimanyisacco.views.transaction;

;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.models.UserTransaction;
import org.pahappa.systems.kimanyisacco.services.SaccoServiceImp;
import org.pahappa.systems.kimanyisacco.services.SaccoServices;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Date;

@ManagedBean (name = "transactionsBean")
@SessionScoped
public class Transactions {
    private UserTransaction userTransaction;

    private final String base = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    private SaccoServices saccoServices;

    private Date systemTime;

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

    public Transactions(){
        this.userTransaction= new UserTransaction();
        this.saccoServices = new SaccoServiceImp();
        this.systemTime = new Date();
    }

    public UserTransaction getUserTransaction() {
        return userTransaction;
    }

    public void setUserTransaction(UserTransaction userTransaction) {
        this.userTransaction = userTransaction;
    }
}
