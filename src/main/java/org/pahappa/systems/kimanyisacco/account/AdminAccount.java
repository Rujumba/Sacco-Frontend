package org.pahappa.systems.kimanyisacco.account;

import org.pahappa.systems.kimanyisacco.controller.Hyperlinks;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.services.SaccoServiceImp;
import org.pahappa.systems.kimanyisacco.services.SaccoServices;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

@SessionScoped
@ManagedBean(name = "adminAccount")
public class AdminAccount {
    private User user;

    private SaccoServices saccoServices;

    private List<User> userList;

    private List<User> allUserList;

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

    public AdminAccount(){
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

        allUserList = saccoServices.getAllUsers();
        userList = saccoServices.getAllUsersOfStatus();
        FacesContext.getCurrentInstance().getExternalContext().redirect(base + Hyperlinks.ADMINACCOUNT);
        for (User user : userList) {
            System.out.println(user.getName());

        }


    }
    public void verfiyUser(User user) throws IOException {
        user.setStatus("verified");
        saccoServices.updateUser(user);
        getUsersOfStatus();
        //message = "User verified successfully";
    }

    public void deleteUser(User user) throws IOException {
        saccoServices.deleteUser(user);
        getUsersOfStatus();
        //message = "User deleted successfully";
    }


    public List<User> getAllUserList() {
        return allUserList;
    }

    public void setAllUserList(List<User> allUserList) {
        this.allUserList = allUserList;
    }
}
