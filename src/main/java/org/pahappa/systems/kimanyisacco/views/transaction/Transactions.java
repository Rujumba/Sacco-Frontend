package org.pahappa.systems.kimanyisacco.transactions;

;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.services.SaccoServiceImp;
import org.pahappa.systems.kimanyisacco.services.SaccoServices;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Date;

@ManagedBean (name = "transactionsBean")
@SessionScoped
public class Transactions {
    private User user;

    private final String base = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    private SaccoServices saccoServices;

    private Date systemTime;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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
        this.user = new User();
        this.saccoServices = new SaccoServiceImp();
        this.systemTime = new Date();
    }
}
