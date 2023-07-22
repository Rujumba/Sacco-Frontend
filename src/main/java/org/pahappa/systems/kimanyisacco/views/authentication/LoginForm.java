package org.pahappa.systems.kimanyisacco.views.authentication;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.pahappa.systems.kimanyisacco.controller.Hyperlinks;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.services.SaccoServiceImp;
import org.pahappa.systems.kimanyisacco.services.SaccoServices;

@ManagedBean(name = "loginForm")
@SessionScoped
public class LoginForm{

    //variables

    private User user;

    private final String base = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    private SaccoServices saccoServices;

    private List<User> users= new ArrayList<>();

    FacesMessage message;

    //getters and setters

    public LoginForm(){

        this.user = new User();
        this.saccoServices =new SaccoServiceImp();
    }

    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }

    public void doLogout() throws IOException {

    }

    public SaccoServices getSaccoServices() {
        return saccoServices;
    }

    public void setSaccoServices(SaccoServices saccoServices) {
        this.saccoServices = saccoServices;
    }

    //methods

    public void doLogin() throws IOException {
        users= saccoServices.getAllUsers();

        for (User user: users) {
            if(user.getEmail().equals(this.user.getEmail()) && user.getPassword().equals(this.user.getPassword())){
                if(this.user.getEmail().contains("admin@gmail.com")){

                    FacesContext.getCurrentInstance().getExternalContext().redirect(base+Hyperlinks.ADMINDASHBOARD);
                }else{
                    FacesContext.getCurrentInstance().getExternalContext().redirect(base+Hyperlinks.DASHBOARD);
                }
            }
            else {
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Warning", "Email or password dont exist! or your account is not verified");
                FacesContext.getCurrentInstance().addMessage("myForm:messages", message);
            }
        }


    }
}
