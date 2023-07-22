package org.pahappa.systems.kimanyisacco.views.dashboard;

import org.pahappa.systems.kimanyisacco.controller.Hyperlinks;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.services.SaccoServiceImp;
import org.pahappa.systems.kimanyisacco.services.SaccoServices;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@SessionScoped
@ManagedBean(name = "dashboard")
public class Dashboard {
    private User user;

    private SaccoServices saccoServices;

    private List<User> userList;

    String base = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();


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

    public Dashboard(){
        this.user = new User();
        this.saccoServices = new SaccoServiceImp();
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void getUsersOfStatus() throws IOException {
        userList = saccoServices.getAllUsersOfStatus();
        FacesContext.getCurrentInstance().getExternalContext().redirect(base + Hyperlinks.ADMINACCOUNT);
        for (User user : userList) {
            System.out.println(user.getName());

        }

    }
}
